package h12.h3_2;

import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
import h12.io.TutorBufferedReader;
import h12.io.TutorFileReader;
import h12.studentexamtableio.SolutionStudentExamTableIO;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.SolutionTableGenerator;
import h12.tableiotest.StudentExamTableIOTestAssumptionsTutorTest;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TestReadStudentExamTableTutorTest {

    private static void createFakeTable() throws IOException {
        TutorFileReader.reset0();
        TutorStudentExamTableIO.reset();
        final StringWriter stringWriter = new StringWriter();
        final TableWithTitle table = SolutionTableGenerator.createTable(20, 20);
        SolutionStudentExamTableIO.writeStudentExamTable(stringWriter, table.getEntries(), table.getTitle());
        final String tableString = stringWriter.toString();
        TutorFileReader.DELEGATE_SUPPLIER = () -> new StringReader(tableString);
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testAssumption() throws IOException {
        TutorIOFactory.reset();
        TutorIOFactory.CREATE_READER = TutorBufferedReader.FS_IO_TUTOR;
        createFakeTable();
        StudentExamTableIOTestAssumptionsTutorTest.testAssume(true, false,
            new StudentExamTableIOTest()::testReadStudentExamTable);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() throws IOException {

        createFakeTable();

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
