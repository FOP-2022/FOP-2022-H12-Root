package h12.h2_5;

import h12.StudentExamEntry;
import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
import h12.io.TutorBufferedWriter;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.TutorTableGenerator;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
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

@TestForSubmission("h12")
public class TestWriteStudentExamTableTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() throws IOException {

        // table generator settings

        TutorTableGenerator.reset();
        TutorTableGenerator.SIZE = size -> assertEquals(50, size, "Expected size 50");
        TutorTableGenerator.USE_SOLUTION = true;
        final List<TableWithTitle> allTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = allTables::add;
        final List<StudentExamEntry[]> allEntries = new ArrayList<>();
        TutorTableGenerator.CREATE_ENTRIES = allEntries::add;

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
        assertFalse(allEntries.isEmpty(), "Did not call TableGenerator#createEntries");
        final int doesNotThrowCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        assertTrue(doesNotThrowCount >= 2,
            "Expected at least two invocations of Assertions#assertDoesNotThrow");

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

        // ensure that all created entries were used
        // in StudentExamTableIO#writeStudentExamTable(Writer, StudentExamTableIO[])
        for (StudentExamEntry[] entries : allEntries) {
            assertTrue(storeWrite.writeData1List.stream().anyMatch(w -> Arrays.equals(w.entries(), entries)),
                "Did not use result from TableGenerator#createEntries in writeStudentExamTable");
        }
    }
}
