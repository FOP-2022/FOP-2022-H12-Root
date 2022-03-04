package h12.h2_1;

import h12.StudentExamEntry;
import h12.StudentExamTableIO;
import h12.SolutionTableGenerator;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOWriteTable2TutorTest {

    @Test
    public void testWriteStudentExamTable2() throws IOException {
        final Random random = new Random(73);
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final StudentExamEntry[] entries = SolutionTableGenerator.createEntries(tableSize, random);

            final StringWriter expectedWriter = new StringWriter();
            H2_1_Utils.writeStudentExamTableTutor(expectedWriter, entries);
            final String[] expected = expectedWriter.toString().split("\n");

            final StringWriter actualWriter = new StringWriter();
            StudentExamTableIO.writeStudentExamTable(actualWriter, entries);
            final String[] actual = actualWriter.toString().split("\n");

            assertArrayEquals(expected, actual, "writeStudentExamTable incorrect for table size " + tableSize);
        }
    }
}
