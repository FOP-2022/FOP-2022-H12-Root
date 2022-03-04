package h12.transform;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.TestAbortedException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Replaces {@link Assumptions}.
 */
@SuppressWarnings("unused")
public class TutorAssumptions {

    public interface Assumption {
        boolean isCorrect();

        default boolean isNotCorrect() {
            return !isCorrect();
        }
    }

    public record TrueAssumption(boolean assumption) implements Assumption {
        @Override
        public boolean isCorrect() {
            return assumption;
        }
    }

    public record FalseAssumption(boolean actual) implements Assumption {
        @Override
        public boolean isCorrect() {
            return !actual;
        }
    }

    public static final List<TrueAssumption> TRUE_ASSUMPTIONS = new ArrayList<>();
    public static final List<FalseAssumption> FALSE_ASSUMPTIONS = new ArrayList<>();
    public static boolean forwardInvocations = false;

    public static void reset() {
        TRUE_ASSUMPTIONS.clear();
        FALSE_ASSUMPTIONS.clear();
        forwardInvocations = false;
    }

    public static Stream<Assumption> streamAllAssumptions() {
        return Stream.concat(TRUE_ASSUMPTIONS.stream(), FALSE_ASSUMPTIONS.stream());
    }

    // --- assumeTrue ----------------------------------------------------

    public static void assumeTrue(boolean assumption) throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumption);
        }
    }

    public static void assumeTrue(BooleanSupplier assumptionSupplier) throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumptionSupplier);
        }
    }

    public static void assumeTrue(BooleanSupplier assumptionSupplier, String message) throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumptionSupplier, message);
        }
    }

    public static void assumeTrue(boolean assumption, Supplier<String> messageSupplier) throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumption, messageSupplier);
        }
    }

    public static void assumeTrue(boolean assumption, String message) throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumption, message);
        }
    }

    public static void assumeTrue(BooleanSupplier assumptionSupplier, Supplier<String> messageSupplier)
        throws TestAbortedException {
        TRUE_ASSUMPTIONS.add(new TrueAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeTrue(assumptionSupplier, messageSupplier);
        }
    }

    // --- assumeFalse ----------------------------------------------------

    public static void assumeFalse(boolean assumption) throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumption);
        }
    }

    public static void assumeFalse(BooleanSupplier assumptionSupplier) throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumptionSupplier);
        }
    }

    public static void assumeFalse(BooleanSupplier assumptionSupplier, String message) throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumptionSupplier, message);
        }
    }

    public static void assumeFalse(boolean assumption, Supplier<String> messageSupplier) throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumption, messageSupplier);
        }
    }

    public static void assumeFalse(boolean assumption, String message) throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumption));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumption, message);
        }
    }

    public static void assumeFalse(BooleanSupplier assumptionSupplier, Supplier<String> messageSupplier)
        throws TestAbortedException {
        FALSE_ASSUMPTIONS.add(new FalseAssumption(assumptionSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assumptions.assumeFalse(assumptionSupplier, messageSupplier);
        }
    }

    // --- assumingThat --------------------------------------------------

    public static void assumingThat(BooleanSupplier assumptionSupplier, Executable executable) {
        if (forwardInvocations) {
            Assumptions.assumingThat(assumptionSupplier, executable);
        }
    }

    public static void assumingThat(boolean assumption, Executable executable) {
        if (forwardInvocations) {
            Assumptions.assumingThat(assumption, executable);
        }
    }
}
