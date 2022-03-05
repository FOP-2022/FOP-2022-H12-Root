package h12.h1_1;

import h12.StudentExamEntry;
import h12.TutorUtils;
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

    @Test
    public void testAttributesExist() {
        Class<?> type = assertClassExists();
        TutorUtils.assertField(type, String.class, true, "firstName");
        TutorUtils.assertField(type, String.class, true, "lastName");
        TutorUtils.assertField(type, int.class, true, "enrollmentNumber");
        TutorUtils.assertField(type, String.class, false, "mark");
    }

    @Test
    public void testConstructorsExist() {
        Class<?> type = assertClassExists();
        assertDoesNotThrow(() -> type.getConstructor(String.class, String.class, int.class),
            "Constructor StudentExamEntry(String, String, int) does not exist");
        assertDoesNotThrow(() -> type.getConstructor(String.class, String.class, int.class, String.class),
            "Constructor StudentExamEntry(String, String, int, String) does not exist");
    }

    @Test
    public void testGettersExist() {
        Class<?> type = assertClassExists();
        TutorUtils.assertGetter(type, "getFirstName", String.class);
        TutorUtils.assertGetter(type, "getLastName", String.class);
        TutorUtils.assertGetter(type, "getEnrollmentNumber", int.class);
        TutorUtils.assertGetter(type, "getMark", String.class);
    }
}
