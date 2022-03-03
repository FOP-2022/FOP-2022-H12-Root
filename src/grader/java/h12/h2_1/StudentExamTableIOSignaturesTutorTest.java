package h12.h2_1;

import h12.StudentExamEntry;
import h12.StudentExamTableIO;
import h12.TutorUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOSignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return StudentExamTableIO.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.StudentExamTableIO"),
                "Class h12.StudentExamTableIO does not exist");
        }
    }

    @Test
    public void testWriteStudentExamEntryExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertMethod(type, "writeStudentExamEntry",
            void.class, Writer.class, StudentExamEntry.class);
    }

    @Test
    public void testWriteStudentExamTableExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertMethod(type, "writeStudentExamTable",
            void.class, Writer.class, StudentExamEntry[].class);
        TutorUtils.assertMethod(type, "writeStudentExamTable",
            void.class, Writer.class, StudentExamEntry[].class, String.class);
    }
}
