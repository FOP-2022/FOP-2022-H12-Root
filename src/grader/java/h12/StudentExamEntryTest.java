package h12;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class StudentExamEntryTest {

    @Test
    public void testAttributes() {
        Class<StudentExamEntry> type = StudentExamEntry.class;
        assertField(type, String.class, true, "firstName");
        assertField(type, String.class, true, "lastName");
        assertField(type, int.class, true, "enrollmentNumber");
        assertField(type, String.class, false, "mark");
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
}
