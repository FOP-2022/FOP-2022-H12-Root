package h12.h4_1;

import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
import h12.io.TutorBufferedWriter;
import h12.io.TutorFileReader;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.TutorTableGenerator;
import h12.tableiotest.StudentExamTableIOTestAssumptionsTutorTest;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static h12.transform.TutorAssertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission("h12")
public class TestWriteStudentExamTableComplexTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testAssumption() throws IOException {
        TutorFileReader.createFakeTable();
        StudentExamTableIOTestAssumptionsTutorTest.checkAssumeBoth(
            new StudentExamTableIOTest()::testWriteStudentExamTableComplex);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() throws IOException {
        TutorAssumptions.reset();
        TutorAssertions.reset();
        TutorFileReader.createFakeTable();

        TutorTableGenerator.reset();
        TutorTableGenerator.SIZE = size -> assertEquals(50, size, "Expected size 50");
        TutorTableGenerator.USE_SOLUTION = true;
        final List<TableWithTitle> allTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = allTables::add;

        // io settings
        final TutorStudentExamTableIO.StoreWrite storeWrite = new TutorStudentExamTableIO.StoreWrite();
        TutorStudentExamTableIO.WRITE = storeWrite;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_INFORM_CUSTOM;

        TutorIOFactory.reset();
        TutorIOFactory.WRITER = true;
        final boolean[] invokeCreateWriter = new boolean[1];
        final List<TutorBufferedWriter> writers = new ArrayList<>();
        TutorIOFactory.CREATE_WRITER = resourceName -> {
            invokeCreateWriter[0] = true;
            final TutorBufferedWriter result = TutorBufferedWriter.FS_IO_TUTOR.apply(resourceName);
            writers.add(result);
            return result;
        };

        // important, otherwise methods from StudentExamTableIO are not called
        TutorAssertions.forwardReturningInvocations = true;
        assertDoesNotThrow(() -> new StudentExamTableIOTest().testWriteStudentExamTable());
        assertTrue(invokeCreateWriter[0], "Did not call IOFactory#createWriter(String)");
        assertFalse(allTables.isEmpty(), "Did not call TableGenerator#createTable");
        final int doesNotThrowCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        assertTrue(doesNotThrowCount >= 1,
            "Expected at least 1 invocation of Assertions#assertDoesNotThrow");

        assertTrue(storeWrite.checkForAll(w -> {
            final Writer wr = w.writer();
            return wr instanceof TutorBufferedWriter && writers.contains(wr);
        }), "Did not use result from IOFactory#createWriter(String)");

        // ensure that all created tables were used
        // in StudentExamTableIO#writeStudentExamTable(Writer, StudentExamTableIO[], String)
        for (TableWithTitle table : allTables) {
            assertTrue(
                storeWrite.writeData0List.stream()
                    .anyMatch(w -> Arrays.equals(w.entries(), table.getEntries()) && w.title().equals(table.getTitle())),
                "Did not use result from TableGenerator#createTable in writeStudentExamTable");
        }
    }
}
