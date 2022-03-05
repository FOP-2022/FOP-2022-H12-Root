package h12.h3_3;

import h12.OverridingTutorStudentExamEntry;
import h12.StudentExamEntry;
import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.transform.TutorAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.BufferedReader;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TestReadStudentExamEntryTutorTest {

    private static final class CustomRead implements TutorStudentExamTableIO.Read {

        private Function<String, StudentExamEntry> entryFunction;

        @Override
        public TableWithTitle readStudentExamTable(final BufferedReader reader) {
            return fail("Used readStudentExamTable");
        }

        @Override
        public StudentExamEntry readStudentExamEntry(final String line) {
            return entryFunction.apply(line);
        }
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() {
        TutorAssertions.reset();
        final StudentExamTableIOTest test = new StudentExamTableIOTest();
        test.testReadStudentExamEntry();
        final int actualCount = TutorAssertions.EQUALS_INVOCATIONS.size();
        if (actualCount < 4) {
            fail("Expected at least 4 invocations of Assertions#assertEquals");
        }
        final CustomRead customRead = new CustomRead();
        TutorStudentExamTableIO.READ = customRead;

        // test with solution
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;
        TutorAssertions.reset();
        TutorAssertions.forwardInvocations = true;
        OverridingTutorStudentExamEntry.reset();
        assertDoesNotThrow(test::testReadStudentExamEntry,
            "testReadStudentExamEntry did not work with solution");

        // break StudentExamEntry and see if the test reacts correctly

        OverridingTutorStudentExamEntry.reset();
        OverridingTutorStudentExamEntry.FIRST_NAME_TRANSFORMER = firstName -> firstName + "a";
        assertThrows(AssertionFailedError.class, test::testReadStudentExamEntry,
            "Broken getFirstName() implementation did not cause test to fail");

        OverridingTutorStudentExamEntry.reset();
        OverridingTutorStudentExamEntry.LAST_NAME_TRANSFORMER = lastName -> lastName + "a";
        assertThrows(AssertionFailedError.class, test::testReadStudentExamEntry,
            "Broken getLastName() implementation did not cause test to fail");

        OverridingTutorStudentExamEntry.reset();
        OverridingTutorStudentExamEntry.ENROLLMENT_NUMBER_TRANSFORMER = enrollmentNumber -> enrollmentNumber + 1;
        assertThrows(AssertionFailedError.class, test::testReadStudentExamEntry,
            "Broken getEnrollmentNumber() implementation did not cause test to fail");

        OverridingTutorStudentExamEntry.reset();
        OverridingTutorStudentExamEntry.MARK_TRANSFORMER = mark -> mark + "a";
        assertThrows(AssertionFailedError.class, test::testReadStudentExamEntry,
            "Broken getMark() implementation did not cause test to fail");
    }
}
