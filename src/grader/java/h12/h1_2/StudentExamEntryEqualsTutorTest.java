package h12.h1_2;

import h12.StudentExamEntry;
import h12.TutorUtils;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamEntryEqualsTutorTest {

    @Test
    public void testBasic() {
        final Random random = new Random(72);
        for (int i = 0; i < 10; i++) {
            final String firstName = TutorUtils.createRandomString(random);
            final String lastName = TutorUtils.createRandomString(random);
            final int enrollmentNumber = random.nextInt(5, 50);
            final int markIndex = random.nextInt(TutorUtils.VALID_MARKS.length);
            final int nextMarkIndex = (markIndex + 1) % TutorUtils.VALID_MARKS.length;
            final String mark = TutorUtils.VALID_MARKS[markIndex];
            final String nextMark = TutorUtils.VALID_MARKS[nextMarkIndex];
            final StudentExamEntry e1 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
            final StudentExamEntry e2 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
            final StudentExamEntry w1 = new StudentExamEntry(firstName + "a", lastName, enrollmentNumber, mark);
            final StudentExamEntry w2 = new StudentExamEntry(lastName, lastName + "a", enrollmentNumber, mark);
            final StudentExamEntry w3 = new StudentExamEntry(lastName, lastName, enrollmentNumber + 1, mark);
            final StudentExamEntry w4 = new StudentExamEntry(lastName, lastName, enrollmentNumber, nextMark);
            assertEquals(e1, e1, "The same entry is not equal with itself");
            assertEquals(e1, e2, "Two equal exam entries did not return true in equals method");
            assertNotEquals(e1, w1, "Different firstName did not result in equals returning false");
            assertNotEquals(e1, w2, "Different lastName did not result in equals returning false");
            assertNotEquals(e1, w3, "Different enrollmentNumber did not result in equals returning false");
            assertNotEquals(e1, w4, "Different mark did not result in equals returning false");
        }
    }

    @Test
    public void testComplex() {
        final Random random = new Random(72);
        for (int i = 0; i < 10; i++) {
            final String firstName = TutorUtils.createRandomString(random);
            final String lastName = TutorUtils.createRandomString(random);
            final int enrollmentNumber = random.nextInt(5, 50);
            final String mark = TutorUtils.createRandomMark(random);
            final StudentExamEntry e1 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
            //noinspection ConstantConditions
            assertFalse(
                assertDoesNotThrow(() -> e1.equals(null),
                    "Unexpected NullPointerException in equals method when given null"
                ),
                "equals method should return false when given null");
            final StudentExamEntry e2 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark) {};
            assertNotEquals(e1, e2, "equals method does not check runtime type of provided Object");
        }
    }
}
