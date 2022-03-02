package h12;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TutorUtils {

    public static final String[] VALID_MARKS = {
        "1,0",
        "1,3",
        "1,7",
        "2,0",
        "2,3",
        "2,7",
        "3,0",
        "3,3",
        "3,7",
        "4,0",
        "5,0",
        "n/a",
    };

    public static String createRandomMark(Random random) {
        return VALID_MARKS[random.nextInt(VALID_MARKS.length)];
    }

    public static String createRandomString(Random random) {
        final int size = random.nextInt(5, 26);
        final char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = TestConstants.A_Z[random.nextInt('z' - 'a')];
        }
        return new String(chars);
    }

    public static void assertTestMethod(Class<?> type, String name) {
        final Method method = assertDoesNotThrow(() -> type.getDeclaredMethod(name));
        assertArrayEquals(new Class<?>[0], method.getParameterTypes(),
            "%s.%s() should have no parameters".formatted(type.getName(), name));
        final Annotation[] annotations = method.getAnnotations();
        assertEquals(1, annotations.length,
            "Expected exactly one annotation on test method %s.%s()".formatted(type.getName(), name));
        assertEquals(Test.class, annotations[0].annotationType(),
            "Expected annotation of type org.junit.jupiter.api.Test on test method %s.%s()".formatted(type.getName(), name));
    }
}
