package h12.h2_1;

import h12.StudentExamTableIO;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.SolutionTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOWriteEntryTutorTest {

    @Test
    public void testWriteStudentExamEntry() throws IOException {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(53);
        for (int i = 0; i < 10; i++) {
            final H2_1_TutorStudentExamEntry entry = H2_1_TutorStudentExamEntry.create(random);
            final StringWriter writer = new StringWriter();
            StudentExamTableIO.writeStudentExamEntry(writer, entry);
            final String actual = writer.toString().replace("\n", "");
            assertEquals(entry.getExpectedOutput(), actual);
        }
    }
}
