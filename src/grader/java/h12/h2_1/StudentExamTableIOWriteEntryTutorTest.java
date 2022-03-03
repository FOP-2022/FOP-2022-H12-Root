package h12.h2_1;

import h12.StudentExamTableIO;
import h12.TutorStudentExamEntry;
import h12.TutorTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

@TestForSubmission("h12")
public class StudentExamTableIOWriteEntryTutorTest {

    @Test
    public void testWriteStudentExamEntry() throws IOException {
        final Random random = new Random(53);
        for (int i = 0; i < 10; i++) {
            final String firstName = TutorTableGenerator.createRandomString(random);
            final String lastName = TutorTableGenerator.createRandomString(random);
            final int enrollmentNumber = random.nextInt(5, 50);
            final String mark = TutorTableGenerator.createRandomMark(random);
            final TutorStudentExamEntry entry = new TutorStudentExamEntry(firstName, lastName, enrollmentNumber, mark);
            final StringWriter writer = new StringWriter();
            StudentExamTableIO.writeStudentExamEntry(writer, entry);
            final String actual = writer.toString().replace("\n", "");
            Assertions.assertEquals(entry.getExpectedOutput(), actual);
        }
    }
}
