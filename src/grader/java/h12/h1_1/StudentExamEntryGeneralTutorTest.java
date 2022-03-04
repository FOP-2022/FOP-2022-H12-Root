package h12.h1_1;

import h12.BadStudentMarkException;
import h12.StudentExamEntry;
import h12.SolutionTableGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamEntryGeneralTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testConstructor3Correct() {
        assertArrayEquals(
            new String[]{"INVOKESPECIAL h12/StudentExamEntry.<init>(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V"},
            StudentExamEntryMeta.ctor3Invokes,
            "h12.StudentExamEntry(String, String, int) should only invoke constructor" +
                "h12.StudentExamEntry(String, String, int, String)"
        );
    }

    private void checkFirstAndLastName(String firstName, String lastName, StudentExamEntry entry) {
        // allow swapped firstName <-> lastName
        if (!firstName.equals(entry.getFirstName()) && !lastName.equals(entry.getFirstName())) {
            fail("firstName incorrect");
        }
        if (!firstName.equals(entry.getLastName()) && !lastName.equals(entry.getLastName())) {
            fail("lastName incorrect");
        }
    }

    @Test
    public void testFieldsAndGetters() {
        final Random random = new Random(87);
        for (int i = 0; i < 10; i++) {
            final String firstName = SolutionTableGenerator.createRandomString(random);
            final String lastName = SolutionTableGenerator.createRandomString(random);
            final int enrollmentNumber = random.nextInt(5, 50);
            final StudentExamEntry e1 = new StudentExamEntry(firstName, lastName, enrollmentNumber);
            checkFirstAndLastName(firstName, lastName, e1);
            assertEquals(enrollmentNumber, e1.getEnrollmentNumber(), "enrollmentNumber incorrect");
            assertEquals("n/a", e1.getMark(), "Mark set by constructor with 3 parameters should have mark 'n/a'");
            final String mark = SolutionTableGenerator.createRandomMark(random);
            final StudentExamEntry e2 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
            checkFirstAndLastName(firstName, lastName, e2);
            assertEquals(enrollmentNumber, e2.getEnrollmentNumber(), "enrollmentNumber incorrect");
            assertEquals(mark, e2.getMark(), "mark incorrect");
        }
    }

    @Test
    public void testSetMark() {
        final Random random = new Random(56);
        final String firstName = SolutionTableGenerator.createRandomString(random);
        final String lastName = SolutionTableGenerator.createRandomString(random);
        final int enrollmentNumber = random.nextInt(5, 50);
        final String mark = SolutionTableGenerator.createRandomMark(random);
        final StudentExamEntry e1 = new StudentExamEntry(firstName, lastName, enrollmentNumber);
        final StudentExamEntry e2 = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
        for (int i = 0; i < 10; i++) {
            final String testMark = SolutionTableGenerator.createRandomMark(random);
            assertDoesNotThrow(() -> e1.setMark(testMark), "setMark threw an exception for mark " + testMark);
            assertDoesNotThrow(() -> e2.setMark(testMark), "setMark threw an exception for mark " + testMark);
            assertEquals(testMark, e1.getMark(), "setMark and/or getMark did correctly handle a set mark");
            assertEquals(testMark, e2.getMark(), "setMark and/or getMark did correctly handle a set mark");
        }
    }

    @Test
    public void testConstructorThrows() {
        assertThrows(NullPointerException.class, () -> new StudentExamEntry(null, "a", 5),
            "Did not throw NPE for firstName");
        assertThrows(NullPointerException.class, () -> new StudentExamEntry("a", null, 5),
            "Did not throw NPE for lastName");
        // TODO: Other exceptions
    }

    private String extractBadStudentMarkExceptionTemplate() {
        // try to infer a template from *assumption* exception message from the students
        // so that marks are not lost on this test if the exception message is incorrect
        try {
            return new BadStudentMarkException("%s").getMessage();
        } catch (Exception ignored) {
            return "Bad student mark '%s'";
        }
    }

    @Test
    public void testSetMarkThrows() {
        final StudentExamEntry entry = new StudentExamEntry("a", "b", 2);
        final String exceptionMessageTemplate = extractBadStudentMarkExceptionTemplate();
        assertThrows(NullPointerException.class, () -> entry.setMark(null),
            "setMark did not throw a NullPointerException when given null");
        final Random random = new Random(28);
        for (int i = 0; i < 10; i++) {
            final String testMark = SolutionTableGenerator.createRandomString(random);
            final BadStudentMarkException e = assertThrows(BadStudentMarkException.class, () -> entry.setMark(testMark),
                "setMark for value '%s' did not throw BadStudentMarkException".formatted(testMark));
            assertEquals(exceptionMessageTemplate.formatted(testMark), e.getMessage());
        }
    }
}
