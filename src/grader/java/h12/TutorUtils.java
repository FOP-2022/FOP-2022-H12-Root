package h12;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TutorUtils {

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

    public static void assertField(Class<?> declaringClass, Class<?> type, boolean isFinal, String fieldName) {
        final Field field = assertDoesNotThrow(() -> declaringClass.getDeclaredField(fieldName),
            "%s does not have field %s".formatted(declaringClass.getName(), fieldName));
        assertEquals(type, field.getType(),
            "%s field %s has incorrect type".formatted(declaringClass.getName(), fieldName));
        if (isFinal) {
            assertTrue(Modifier.isFinal(field.getModifiers()),
                "%s field %s is not final".formatted(declaringClass.getName(), fieldName));
        } else {
            assertFalse(Modifier.isFinal(field.getModifiers()),
                "%s field %s is final".formatted(declaringClass.getName(), fieldName));
        }
    }

    public static void assertGetter(Class<?> type, String name, Class<?> returnType) {
        Method method = assertDoesNotThrow(() -> type.getMethod(name),
            "%s.%s() does not exist".formatted(type.getName(), name));
        assertEquals(returnType, method.getReturnType(),
            "%s.%s() has incorrect return type %s".formatted(type.getName(), name, method.getReturnType().getName()));
        assertArrayEquals(new Class<?>[0], method.getParameterTypes(),
            "%s.%s() should have no parameters".formatted(type.getName(), name));
    }

    public static void assertMethod(Class<?> type, String name, Class<?> returnType, Class<?>... parameterTypes) {
        final String signature = "%s.%s(%s)".formatted(type.getName(), name,
            Stream.of(parameterTypes).map(Class::getSimpleName).collect(Collectors.joining(", ")));
        Method method = assertDoesNotThrow(() -> type.getMethod(name, parameterTypes),
            "%s does not exist".formatted(signature));
        assertEquals(returnType, method.getReturnType(),
            "%s has incorrect return type %s".formatted(signature, method.getReturnType().getName()));
        assertArrayEquals(parameterTypes, method.getParameterTypes(),
            "%s has incorrect parameters".formatted(signature));
    }
}
