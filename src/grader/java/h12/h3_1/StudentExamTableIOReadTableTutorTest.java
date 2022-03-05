package h12.h3_1;

import h12.StudentExamEntry;
import h12.StudentExamTableIO;
import h12.TableWithTitle;
import h12.studentexamtableio.SolutionStudentExamTableIO;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.SolutionTableGenerator;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOReadTableTutorTest {

    @Test
    public void testReadStudentExamTableNoTitle() throws IOException {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(38);
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final StudentExamEntry[] entries = SolutionTableGenerator.createEntries(tableSize, random);

            final StringWriter writer = new StringWriter();
            SolutionStudentExamTableIO.writeStudentExamTable(writer, entries);

            final StringReader reader = new StringReader(writer.toString());
            final TableWithTitle actual = StudentExamTableIO.readStudentExamTable(new BufferedReader(reader));
            assertNull(actual.getTitle(), "expected no title");
            assertArrayEquals(entries, actual.getEntries(), "read-back entries are incorrect for size " + tableSize);
        }
    }

    @Test
    public void testReadStudentExamTableWithTitle() throws IOException {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(39);
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final TableWithTitle table = SolutionTableGenerator.createTable(tableSize, random);

            final StringWriter writer = new StringWriter();
            SolutionStudentExamTableIO.writeStudentExamTable(writer, table.getEntries(), table.getTitle());

            final StringReader reader = new StringReader(writer.toString());
            final TableWithTitle actual = StudentExamTableIO.readStudentExamTable(new BufferedReader(reader));
            assertEquals(table.getTitle(), actual.getTitle(), "incorrect title");
            assertArrayEquals(table.getEntries(), actual.getEntries(), "read-back entries are incorrect for size " + tableSize);
        }
    }
}
