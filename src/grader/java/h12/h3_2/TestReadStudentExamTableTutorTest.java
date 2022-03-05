package h12.h3_2;

import h12.StudentExamTableIOTest;
import h12.io.TutorBufferedReader;
import h12.io.TutorFileReader;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tableiotest.StudentExamTableIOTestAssumptionsTutorTest;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TestReadStudentExamTableTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testAssumption() throws IOException {
        TutorFileReader.createFakeTable();
        StudentExamTableIOTestAssumptionsTutorTest.checkAssumeReader(
            new StudentExamTableIOTest()::testReadStudentExamTable);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() throws IOException {

        TutorFileReader.createFakeTable();

        // io settings
        final TutorStudentExamTableIO.StoreRead storeRead = new TutorStudentExamTableIO.StoreRead();
        TutorStudentExamTableIO.READ = storeRead;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_INFORM_CUSTOM;
        TutorIOFactory.reset();
        TutorIOFactory.READER = true;
        final boolean[] invokeCreateReader = new boolean[1];
        final List<TutorBufferedReader> readers = new ArrayList<>();
        TutorIOFactory.CREATE_READER = resourceName -> {
            invokeCreateReader[0] = true;
            final TutorBufferedReader result = TutorBufferedReader.FS_IO_TUTOR.apply(resourceName);
            readers.add(result);
            return result;
        };

        // important, otherwise methods from StudentExamTableIO are not called
        TutorAssertions.forwardReturningInvocations = true;
        TutorAssumptions.reset();
        new StudentExamTableIOTest().testReadStudentExamTable();
        assertTrue(invokeCreateReader[0], "Did not call IOFactory#createWriter(String)");
        final int doesNotThrowCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        assertTrue(doesNotThrowCount >= 1,
            "Expected at least 1 invocation of Assertions#assertDoesNotThrow");

        assertTrue(storeRead.readers.stream().allMatch(r -> r instanceof TutorBufferedReader && readers.contains(r)),
            "Did not use result from IOFactory#createReader(String)");
    }
}
