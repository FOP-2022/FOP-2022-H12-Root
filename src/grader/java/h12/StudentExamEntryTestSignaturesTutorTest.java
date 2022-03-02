package h12;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import static org.junit.jupiter.api.Assertions.*;

public class StudentExamEntryTestSignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return StudentExamEntryTest.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.StudentExamEntryTest"),
                "Class h12.StudentExamEntryTest does not exist");
        }
    }

    @Test
    public void testSignaturesCorrect() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testConstructorsWork");
        TutorUtils.assertTestMethod(type, "testConstructorsThrow");
        TutorUtils.assertTestMethod(type, "testMarks");
    }
}
