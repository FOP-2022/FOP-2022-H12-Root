package h12;

import h12.h1_3.SetMark_TutorStudentExamEntry;
import h12.transform.TutorAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;
import org.sourcegrade.jagr.launcher.env.Jagr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class StudentExamEntryTestFunctionTutorTest {

    private void invokeSafe(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            Jagr.Default.getInjector().getInstance(Logger.class)
                .error("Transformation thing failed " + e.getClass().getSimpleName() + " :: " + e.getMessage()
                    + " @ " + e.getStackTrace()[0], e);
            fail("Transformations in StudentExamEntryTest#testConstructorsThrow(). Please contact your Tutor.");
        }
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testTestConstructorsWork() {
        TutorAssertions.reset();
        invokeSafe(new StudentExamEntryTest()::testConstructorsWork);
        final int actualCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        if (actualCount < 2) {
            fail("Expected at least 2 invocations of Assertions#assertDoesNotThrow but got " + actualCount);
        }
    }

    private void checkAssertThrows(Class<? extends Throwable> exceptionType, long count) {
        final long actualCount = TutorAssertions.THROWS_INVOCATIONS.stream()
            .filter(t -> t.expectedType().equals(exceptionType)).count();
        assertTrue(actualCount >= count,
            "Expected at least " + count + " invocation(s) of Assertions#assertThrows for "
                + exceptionType.getSimpleName() + " but got " + actualCount);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testTestConstructorsThrowSimple() {
        TutorAssertions.reset();
        invokeSafe(new StudentExamEntryTest()::testConstructorsThrow);
        checkAssertThrows(NullPointerException.class, 3);
        checkAssertThrows(BadEnrollmentNumberException.class, 1);
        checkAssertThrows(BadCharException.class, 1);
    }

    private void checkAssertEquals(String startsWith, long count) {
        final long actualCount = TutorAssertions.EQUALS_INVOCATIONS.stream()
            .filter(t -> t.expected().toString().startsWith(startsWith)).count();
        assertTrue(actualCount >= count,
            "Expected at least " + count + " invocation of Assertions#assertEquals starting with '"
                + startsWith + "' but got " + actualCount);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testTestConstructorsThrowComplex() {
        TutorAssertions.reset();
        invokeSafe(new StudentExamEntryTest()::testConstructorsThrow);
        checkAssertEquals("Bad enrollment number", 1);
        checkAssertEquals("Bad char", 1);
    }

    private void checkAssertDoesNotThrow(long count) {
        final long actualCount = TutorAssertions.DOES_NOT_THROW_INVOCATIONS.size();
        assertTrue(actualCount >= count,
            "Expected at least " + count + " invocation(s) of Assertions#assertDoesNotThrow"
                + " but got " + actualCount);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testTestMarks() {
        TutorAssertions.reset();
        TutorAssertions.forwardReturningInvocations = true;
        // marks that were passed to setMark method
        final Set<String> setMarks = new HashSet<>();
        SetMark_TutorStudentExamEntry.SET_MARK_CONSUMER = mark -> {
            setMarks.add(mark);
            return true; // invoke super.setMark
        };
        invokeSafe(new StudentExamEntryTest()::testMarks);
        checkAssertThrows(BadStudentMarkException.class, 1);
        checkAssertThrows(NullPointerException.class, 1);
        checkAssertDoesNotThrow(1);

        // check that all valid mark values are tested
        final List<String> validMarks = List.of(TutorUtils.VALID_MARKS);
        final Set<String> testedMarks = new HashSet<>();
        for (TutorAssertions.EqualsInvocation invocation : TutorAssertions.EQUALS_INVOCATIONS) {
            final String potentialMark = invocation.expected().toString();
            if (validMarks.contains(potentialMark)) {
                testedMarks.add(potentialMark);
            }
        }
        final List<String> missingTestMarks = new ArrayList<>(validMarks);
        missingTestMarks.removeAll(testedMarks);
        assertTrue(missingTestMarks.isEmpty(),
            "Did not test all valid marks. Missing: " + missingTestMarks);
        final List<String> missingSetMarks = new ArrayList<>(validMarks);
        missingSetMarks.removeAll(setMarks);
        assertTrue(missingSetMarks.isEmpty(),
            "Did not set all valid marks. Missing: " + missingSetMarks);
        assertTrue(setMarks.contains(null),
            "Did not try to pass null to setMark");
    }
}
