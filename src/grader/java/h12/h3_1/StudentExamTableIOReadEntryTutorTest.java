package h12.h3_1;

import h12.StudentExamEntry;
import h12.StudentExamTableIO;
import h12.h2_1.H2_1_TutorStudentExamEntry;
import h12.studentexamtableio.TutorStudentExamTableIO;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamTableIOReadEntryTutorTest {

    @Test
    public void testReadStudentExamEntry() {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(49);
        for (int i = 0; i < 10; i++) {
            final H2_1_TutorStudentExamEntry entry = H2_1_TutorStudentExamEntry.create(random);
            final StudentExamEntry actual = StudentExamTableIO.readStudentExamEntry(entry.getExpectedOutput());
            assertEquals(entry.getFirstName(), actual.getFirstName(), "incorrect firstName");
            assertEquals(entry.getLastName(), actual.getLastName(), "incorrect lastName");
            assertEquals(entry.getEnrollmentNumber(), actual.getEnrollmentNumber(), "incorrect enrollmentNumber");
            assertEquals(entry.getMark(), actual.getMark(), "incorrect mark");
        }
    }
}
