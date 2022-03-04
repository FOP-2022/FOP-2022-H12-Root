package h12.h3_2;

import h12.StudentExamTableIOTest;
import h12.io.TutorBufferedReader;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission("h12")
public class TestReadStudentExamTableTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() {

        // io settings
        final TutorStudentExamTableIO.StoreRead storeRead = new TutorStudentExamTableIO.StoreRead();
        TutorStudentExamTableIO.READ = storeRead;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_CUSTOM;

        TutorIOFactory.reset();
        TutorIOFactory.WRITER = true;
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
        assertDoesNotThrow(() -> new StudentExamTableIOTest().testReadStudentExamTable());
        assertTrue(invokeCreateReader[0], "Did not call IOFactory#createWriter(String)");
        final int doesNotThrowCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        assertTrue(doesNotThrowCount >= 1,
            "Expected at least 1 invocation of Assertions#assertDoesNotThrow");

        assertTrue(storeRead.readers.stream().allMatch(r -> r instanceof TutorBufferedReader && readers.contains(r)),
            "Did not use result from IOFactory#createReader(String)");
    }
}
