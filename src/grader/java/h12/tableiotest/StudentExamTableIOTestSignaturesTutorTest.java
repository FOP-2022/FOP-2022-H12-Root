package h12.tableiotest;

import h12.IOFactory;
import h12.StudentExamTableIOTest;
import h12.TutorUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOTestSignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return StudentExamTableIOTest.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.StudentExamTableIOTest"),
                "Class h12.StudentExamTableIOTest does not exist");
        }
    }

    @Test
    public void testIOFactoryFieldExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertField(type, IOFactory.class, true, "ioFactory");
    }

    @Test
    public void testTestWriteStudentExamTableExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testWriteStudentExamTable");
    }

    @Test
    public void testTestReadStudentExamTableExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testReadStudentExamTable");
    }

    @Test
    public void testTestReadStudentExamEntryExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testReadStudentExamEntry");
    }

    @Test
    public void testTestWriteStudentExamTableComplexExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testWriteStudentExamTableComplex");
    }

    @Test
    public void testTestWriteAndReadStudentExamTableExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertTestMethod(type, "testWriteAndReadStudentExamTable");
    }
}
