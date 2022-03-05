package h12.h2_1;

import h12.studentexamtableio.SolutionStudentExamTableIO;
import h12.StudentExamTableIO;
import h12.TableWithTitle;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.SolutionTableGenerator;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOWriteTable3TutorTest {

    @Test
    public void testWriteStudentExamTable3() throws IOException {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(73);
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final TableWithTitle table = SolutionTableGenerator.createTable(tableSize, random);

            final StringWriter expectedWriter = new StringWriter();
            SolutionStudentExamTableIO.writeStudentExamTable(expectedWriter, table.getEntries(), table.getTitle());
            final String[] expected = expectedWriter.toString().split("\n");

            final StringWriter actualWriter = new StringWriter();
            StudentExamTableIO.writeStudentExamTable(actualWriter, table.getEntries(), table.getTitle());
            final String[] actual = actualWriter.toString().split("\n");

            assertArrayEquals(expected, actual, "writeStudentExamTable incorrect for table size " + tableSize);
        }
    }
}
