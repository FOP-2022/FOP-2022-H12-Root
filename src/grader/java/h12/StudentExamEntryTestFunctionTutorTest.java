package h12;

import h12.transform.TutorAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamEntryTestFunctionTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testTestConstructorsWork() {
        TutorAssertions.reset();
        try {
            new StudentExamEntryTest().testConstructorsThrow();
        } catch (Throwable ignored) {
            fail("Transformations in StudentExamEntryTest#testConstructorsThrow(). Please contact your Tutor.");
        }
        if (TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size() < 2) {
            fail("Expected at least two invocations of Assertions#assertDoesNotThrow");
        }
    }
}
