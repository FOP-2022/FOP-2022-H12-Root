package h12.h1_1;

import h12.StudentExamEntry;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamEntrySignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return StudentExamEntry.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.StudentExamEntry"),
                "Class h12.StudentExamEntry does not exist");
        }
    }

    private void assertField(Class<?> declaringClass, Class<?> type, boolean isFinal, String fieldName) {
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

    @Test
    public void testAttributesExist() {
        Class<?> type = assertClassExists();
        assertField(type, String.class, true, "firstName");
        assertField(type, String.class, true, "lastName");
        assertField(type, int.class, true, "enrollmentNumber");
        assertField(type, String.class, false, "mark");
    }

    @Test
    public void testConstructorsExist() {
        Class<?> type = assertClassExists();
        assertDoesNotThrow(() -> type.getConstructor(String.class, String.class, int.class),
            "Constructor StudentExamEntry(String, String, int) does not exist");
        assertDoesNotThrow(() -> type.getConstructor(String.class, String.class, int.class, String.class),
            "Constructor StudentExamEntry(String, String, int, String) does not exist");
    }

    private void assertGetter(Class<?> type, String name, Class<?> returnType) {
        Method method = assertDoesNotThrow(() -> type.getMethod(name),
            "%s.%s() does not exist".formatted(type.getName(), name));
        assertEquals(returnType, method.getReturnType(),
            "%s.%s() has incorrect return type %s".formatted(type.getName(), name, method.getReturnType().getName()));
        assertArrayEquals(new Class<?>[0], method.getParameterTypes(),
            "%s.%s() should have no parameters".formatted(type.getName(), name));
    }

    @Test
    public void testGettersExist() {
        Class<?> type = assertClassExists();
        assertGetter(type, "getFirstName", String.class);
        assertGetter(type, "getLastName", String.class);
        assertGetter(type, "getEnrollmentNumber", int.class);
        assertGetter(type, "getMark", String.class);
    }

    @Test
    public void assertSetMarkExists() {

    }
}
