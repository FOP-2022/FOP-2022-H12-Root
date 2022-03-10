package h12.transform;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.opentest4j.MultipleFailuresError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Replaces {@link Assertions}.
 */
@SuppressWarnings("unused")
public class TutorAssertions {

    public record FailInvocation(@Nullable String message, @Nullable Throwable throwable) {}

    public record TrueInvocation(boolean actual) {}

    public record FalseInvocation(boolean actual) {}

    public record NullInvocation(@Nullable Object actual) {}

    public record NotNullInvocation(@Nullable Object actual) {}

    public record EqualsInvocation(Object expected, Object actual) {}

    public record ArrayEqualsInvocation(Object expected, Object actual) {}

    public record NotEqualsInvocation(Object expected, Object actual) {}

    public record SameInvocation(Object expected, Object actual) {}

    public record NotSameInvocation(Object unexpected, Object actual) {}

    public record AllInvocation(Collection<Executable> executables) {}

    public record ThrowsInvocation<T extends Throwable>(Class<T> expectedType, Executable executable) {}

    public record DoesNotThrowInvocation<T>(ThrowingSupplier<T> supplier) {}

    public record InstanceOfInvocation<T>(Class<T> expectedType, Object actual) {}

    public static final List<FailInvocation> FAIL_INVOCATIONS = new ArrayList<>();
    public static final List<TrueInvocation> TRUE_INVOCATIONS = new ArrayList<>();
    public static final List<FalseInvocation> FALSE_INVOCATIONS = new ArrayList<>();
    public static final List<NullInvocation> NULL_INVOCATIONS = new ArrayList<>();
    public static final List<NotNullInvocation> NOT_NULL_INVOCATIONS = new ArrayList<>();
    public static final List<EqualsInvocation> EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<ArrayEqualsInvocation> ARRAY_EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<NotEqualsInvocation> NOT_EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<SameInvocation> SAME_INVOCATIONS = new ArrayList<>();
    public static final List<NotSameInvocation> NOT_SAME_INVOCATIONS = new ArrayList<>();
    public static final List<AllInvocation> ALL_INVOCATIONS = new ArrayList<>(); // invocations of assertAll, not _all_
    public static final List<ThrowsInvocation<?>> THROWS_INVOCATIONS = new ArrayList<>();
    public static final List<DoesNotThrowInvocation<?>> DOES_NOT_THROW_INVOCATIONS = new ArrayList<>();
    public static final List<InstanceOfInvocation<?>> INSTANCE_OF_INVOCATIONS = new ArrayList<>();
    public static boolean forwardInvocations = false;
    public static boolean forwardReturningInvocations = false;

    public static void reset() {
        FAIL_INVOCATIONS.clear();
        TRUE_INVOCATIONS.clear();
        FALSE_INVOCATIONS.clear();
        NULL_INVOCATIONS.clear();
        NOT_NULL_INVOCATIONS.clear();
        EQUALS_INVOCATIONS.clear();
        NOT_EQUALS_INVOCATIONS.clear();
        ARRAY_EQUALS_INVOCATIONS.clear();
        ALL_INVOCATIONS.clear();
        THROWS_INVOCATIONS.clear();
        DOES_NOT_THROW_INVOCATIONS.clear();
        forwardInvocations = false;
        forwardReturningInvocations = false;
    }

    // --- fail ----------------------------------------------------------------

    public static <V> V fail() {
        FAIL_INVOCATIONS.add(new FailInvocation(null, null));
        if (forwardInvocations) {
            Assertions.fail();
        }
        return null;
    }

    public static <V> V fail(String message) {
        FAIL_INVOCATIONS.add(new FailInvocation(message, null));
        if (forwardInvocations) {
            Assertions.fail(message);
        }
        return null;
    }

    public static <V> V fail(String message, Throwable cause) {
        FAIL_INVOCATIONS.add(new FailInvocation(message, cause));
        if (forwardInvocations) {
            Assertions.fail(message, cause);
        }
        return null;
    }

    public static <V> V fail(Throwable cause) {
        FAIL_INVOCATIONS.add(new FailInvocation(null, cause));
        if (forwardInvocations) {
            Assertions.fail(cause);
        }
        return null;
    }

    public static <V> V fail(Supplier<String> messageSupplier) {
        FAIL_INVOCATIONS.add(new FailInvocation(messageSupplier.get(), null));
        if (forwardInvocations) {
            Assertions.fail(messageSupplier);
        }
        return null;
    }

    // --- assertTrue ----------------------------------------------------------

    public static void assertTrue(boolean condition) {
        TRUE_INVOCATIONS.add(new TrueInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertTrue(condition);
        }
    }

    public static void assertTrue(boolean condition, Supplier<String> messageSupplier) {
        TRUE_INVOCATIONS.add(new TrueInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertTrue(condition, messageSupplier);
        }
    }

    public static void assertTrue(BooleanSupplier booleanSupplier) {
        TRUE_INVOCATIONS.add(new TrueInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertTrue(booleanSupplier);
        }
    }

    public static void assertTrue(BooleanSupplier booleanSupplier, String message) {
        TRUE_INVOCATIONS.add(new TrueInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertTrue(booleanSupplier, message);
        }
    }

    public static void assertTrue(boolean condition, String message) {
        TRUE_INVOCATIONS.add(new TrueInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertTrue(condition, message);
        }
    }

    public static void assertTrue(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier) {
        TRUE_INVOCATIONS.add(new TrueInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertTrue(booleanSupplier, messageSupplier);
        }
    }

    // --- assertFalse ---------------------------------------------------------

    public static void assertFalse(boolean condition) {
        FALSE_INVOCATIONS.add(new FalseInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertFalse(condition);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        FALSE_INVOCATIONS.add(new FalseInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertFalse(condition, message);
        }
    }

    public static void assertFalse(boolean condition, Supplier<String> messageSupplier) {
        FALSE_INVOCATIONS.add(new FalseInvocation(condition));
        if (forwardInvocations) {
            Assertions.assertFalse(condition, messageSupplier);
        }
    }

    public static void assertFalse(BooleanSupplier booleanSupplier) {
        FALSE_INVOCATIONS.add(new FalseInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertFalse(booleanSupplier);
        }
    }

    public static void assertFalse(BooleanSupplier booleanSupplier, String message) {
        FALSE_INVOCATIONS.add(new FalseInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertFalse(booleanSupplier, message);
        }
    }

    public static void assertFalse(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier) {
        FALSE_INVOCATIONS.add(new FalseInvocation(booleanSupplier.getAsBoolean()));
        if (forwardInvocations) {
            Assertions.assertFalse(booleanSupplier, messageSupplier);
        }
    }

    // --- assertNull ----------------------------------------------------------

    public static void assertNull(Object actual) {
        NULL_INVOCATIONS.add(new NullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNull(actual);
        }
    }

    public static void assertNull(Object actual, String message) {
        NULL_INVOCATIONS.add(new NullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNull(actual, message);
        }
    }

    public static void assertNull(Object actual, Supplier<String> messageSupplier) {
        NULL_INVOCATIONS.add(new NullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNull(actual, messageSupplier);
        }
    }

    // --- assertNotNull -------------------------------------------------------

    public static void assertNotNull(Object actual) {
        NOT_NULL_INVOCATIONS.add(new NotNullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNotNull(actual);
        }
    }

    public static void assertNotNull(Object actual, String message) {
        NOT_NULL_INVOCATIONS.add(new NotNullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNotNull(actual, message);
        }
    }

    public static void assertNotNull(Object actual, Supplier<String> messageSupplier) {
        NOT_NULL_INVOCATIONS.add(new NotNullInvocation(actual));
        if (forwardInvocations) {
            Assertions.assertNotNull(actual, messageSupplier);
        }
    }

    // --- assertEquals --------------------------------------------------------

    public static void assertEquals(short expected, short actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(short expected, Short actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Short expected, short actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Short expected, Short actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(short expected, short actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(short expected, Short actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Short expected, short actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Short expected, Short actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(short expected, short actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(short expected, Short actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Short expected, short actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Short expected, Short actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(byte expected, byte actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(byte expected, Byte actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Byte expected, byte actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Byte expected, Byte actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(byte expected, byte actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(byte expected, Byte actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Byte expected, byte actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Byte expected, Byte actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(byte expected, byte actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(byte expected, Byte actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Byte expected, byte actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Byte expected, Byte actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(int expected, int actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, Integer actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Integer expected, int actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Integer expected, Integer actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, int actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, Integer actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Integer expected, int actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Integer expected, Integer actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(int expected, int actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(int expected, Integer actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Integer expected, int actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Integer expected, Integer actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(long expected, long actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(long expected, Long actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Long expected, long actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Long expected, Long actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(long expected, long actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(long expected, Long actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Long expected, long actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Long expected, Long actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(long expected, long actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(long expected, Long actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Long expected, long actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Long expected, Long actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, float actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(float expected, Float actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Float expected, float actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Float expected, Float actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(float expected, float actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(float expected, Float actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Float expected, float actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Float expected, Float actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(float expected, float actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, Float actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Float expected, float actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Float expected, Float actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, float actual, float delta) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta);
        }
    }

    public static void assertEquals(float expected, float actual, float delta, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta, message);
        }
    }

    public static void assertEquals(float expected, float actual, float delta, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertEquals(double expected, double actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(double expected, Double actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Double expected, double actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Double expected, Double actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(double expected, double actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(double expected, Double actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Double expected, double actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Double expected, Double actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(double expected, double actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(double expected, Double actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Double expected, double actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Double expected, Double actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(double expected, double actual, double delta) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta);
        }
    }

    public static void assertEquals(double expected, double actual, double delta, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta, message);
        }
    }

    public static void assertEquals(double expected, double actual, double delta, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertEquals(char expected, char actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));

        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, Character actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Character expected, char actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Character expected, Character actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, char actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, Character actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Character expected, char actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Character expected, Character actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(char expected, char actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(char expected, Character actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Character expected, char actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Character expected, Character actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Object expected, Object actual, Supplier<String> messageSupplier) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    // --- assertArrayEquals ---------------------------------------------------

    public static void assertArrayEquals(boolean[] expected, boolean[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(boolean[] expected, boolean[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(boolean[] expected, boolean[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertArrayEquals(char[] expected, char[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(char[] expected, char[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(char[] expected, char[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertArrayEquals(byte[] expected, byte[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(byte[] expected, byte[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(byte[] expected, byte[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier.get());
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(short[] expected, short[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier.get());
        }
    }

    public static void assertArrayEquals(int[] expected, int[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(int[] expected, int[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(int[] expected, int[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertArrayEquals(long[] expected, long[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(long[] expected, long[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(long[] expected, long[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier.get());
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual, float delta) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta);
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual, float delta, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta, message);
        }
    }

    public static void assertArrayEquals(float[] expected, float[] actual, float delta,
                                         Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual, double delta) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual, double delta, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta, message);
        }
    }

    public static void assertArrayEquals(double[] expected, double[] actual, double delta,
                                         Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertArrayEquals(Object[] expected, Object[] actual) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual);
        }
    }

    public static void assertArrayEquals(Object[] expected, Object[] actual, String message) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, message);
        }
    }

    public static void assertArrayEquals(Object[] expected, Object[] actual, Supplier<String> messageSupplier) {
        ARRAY_EQUALS_INVOCATIONS.add(new ArrayEqualsInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertArrayEquals(expected, actual, messageSupplier);
        }
    }

    // --- assertNotEquals -----------------------------------------------------

    public static void assertNotEquals(short unexpected, short actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(short unexpected, Short actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Short unexpected, short actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Short unexpected, Short actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(short unexpected, short actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(short unexpected, Short actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Short unexpected, short actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Short unexpected, Short actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(short unexpected, short actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(short unexpected, Short actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Short unexpected, short actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Short unexpected, Short actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(byte unexpected, byte actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(byte unexpected, Byte actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Byte unexpected, byte actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Byte unexpected, Byte actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(byte unexpected, byte actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(byte unexpected, Byte actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Byte unexpected, byte actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Byte unexpected, Byte actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(byte unexpected, byte actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(byte unexpected, Byte actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Byte unexpected, byte actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Byte unexpected, Byte actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(int unexpected, int actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(int unexpected, Integer actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Integer unexpected, int actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Integer unexpected, Integer actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(int unexpected, int actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(int unexpected, Integer actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Integer unexpected, int actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Integer unexpected, Integer actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(int unexpected, int actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(int unexpected, Integer actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Integer unexpected, int actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Integer unexpected, Integer actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(long unexpected, long actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(long unexpected, Long actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Long unexpected, long actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Long unexpected, Long actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(long unexpected, long actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(long unexpected, Long actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Long unexpected, long actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Long unexpected, Long actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(long unexpected, long actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(long unexpected, Long actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Long unexpected, long actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Long unexpected, Long actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(float unexpected, float actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(float unexpected, Float actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Float unexpected, float actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Float unexpected, Float actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(float unexpected, Float actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Float unexpected, float actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Float unexpected, Float actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(float unexpected, Float actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Float unexpected, float actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Float unexpected, Float actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, float delta) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, float delta, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta, message);
        }
    }

    public static void assertNotEquals(float unexpected, float actual, float delta, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta, messageSupplier);
        }
    }

    public static void assertNotEquals(double unexpected, double actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(double unexpected, Double actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Double unexpected, double actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Double unexpected, Double actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(double unexpected, Double actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Double unexpected, double actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Double unexpected, Double actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(double unexpected, Double actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Double unexpected, double actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Double unexpected, Double actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, double delta) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, double delta, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta, message);
        }
    }

    public static void assertNotEquals(double unexpected, double actual, double delta, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, delta, messageSupplier);
        }
    }

    public static void assertNotEquals(char unexpected, char actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));

        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(char unexpected, Character actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Character unexpected, char actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Character unexpected, Character actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(char unexpected, char actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(char unexpected, Character actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Character unexpected, char actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Character unexpected, Character actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(char unexpected, char actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(char unexpected, Character actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Character unexpected, char actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Character unexpected, Character actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    public static void assertNotEquals(Object unexpected, Object actual) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual);
        }
    }

    public static void assertNotEquals(Object unexpected, Object actual, String message) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, message);
        }
    }

    public static void assertNotEquals(Object unexpected, Object actual, Supplier<String> messageSupplier) {
        NOT_EQUALS_INVOCATIONS.add(new NotEqualsInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertNotEquals(unexpected, actual, messageSupplier);
        }
    }

    // --- assertSame ----------------------------------------------------------

    public static void assertSame(Object expected, Object actual) {
        SAME_INVOCATIONS.add(new SameInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(expected, actual);
        }
    }

    public static void assertSame(Object expected, Object actual, String message) {
        SAME_INVOCATIONS.add(new SameInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(expected, actual, message);
        }
    }

    public static void assertSame(Object expected, Object actual, Supplier<String> messageSupplier) {
        SAME_INVOCATIONS.add(new SameInvocation(expected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(expected, actual, messageSupplier);
        }
    }

    // --- assertNotSame ----------------------------------------------------------

    public static void assertNotSame(Object unexpected, Object actual) {
        NOT_SAME_INVOCATIONS.add(new NotSameInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(unexpected, actual);
        }
    }

    public static void assertNotSame(Object unexpected, Object actual, String message) {
        NOT_SAME_INVOCATIONS.add(new NotSameInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(unexpected, actual, message);
        }
    }

    public static void assertNotSame(Object unexpected, Object actual, Supplier<String> messageSupplier) {
        NOT_SAME_INVOCATIONS.add(new NotSameInvocation(unexpected, actual));
        if (forwardInvocations) {
            Assertions.assertSame(unexpected, actual, messageSupplier);
        }
    }

    // --- assertAll -----------------------------------------------------------

    public static void assertAll(Executable... executables) throws MultipleFailuresError {
        ALL_INVOCATIONS.add(new AllInvocation(List.of(executables)));
        if (forwardInvocations) {
            Assertions.assertAll(executables);
        }
    }

    public static void assertAll(String heading, Executable... executables) throws MultipleFailuresError {
        ALL_INVOCATIONS.add(new AllInvocation(List.of(executables)));
        if (forwardInvocations) {
            Assertions.assertAll(heading, executables);
        }
    }

    public static void assertAll(Collection<Executable> executables) throws MultipleFailuresError {
        ALL_INVOCATIONS.add(new AllInvocation(executables));
        if (forwardInvocations) {
            Assertions.assertAll(executables);
        }
    }

    public static void assertAll(String heading, Collection<Executable> executables) throws MultipleFailuresError {
        ALL_INVOCATIONS.add(new AllInvocation(executables));
        if (forwardInvocations) {
            Assertions.assertAll(heading, executables);
        }
    }

    public static void assertAll(Stream<Executable> executables) throws MultipleFailuresError {
        List<Executable> executableList = new ArrayList<>();
        Stream<Executable> newExecutableStream = executables.peek(executableList::add);
        ALL_INVOCATIONS.add(new AllInvocation(executableList));
        if (forwardInvocations) {
            Assertions.assertAll(newExecutableStream);
        }
    }

    public static void assertAll(String heading, Stream<Executable> executables) throws MultipleFailuresError {
        List<Executable> executableList = new ArrayList<>();
        Stream<Executable> newExecutableStream = executables.peek(executableList::add);
        ALL_INVOCATIONS.add(new AllInvocation(executableList));
        if (forwardInvocations) {
            Assertions.assertAll(heading, newExecutableStream);
        }
    }

    // --- assert exceptions ---------------------------------------------------

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrowsExactly(Class<T> expectedType, Executable executable) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrowsExactly(expectedType, executable);
        } else {
            return (T) new Throwable();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrowsExactly(Class<T> expectedType, Executable executable, String message) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrowsExactly(expectedType, executable, message);
        } else {
            return (T) new Throwable();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrowsExactly(Class<T> expectedType, Executable executable,
                                                              Supplier<String> messageSupplier) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrowsExactly(expectedType, executable, messageSupplier);
        } else {
            return (T) new Throwable();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrows(expectedType, executable);
        } else {
            return (T) new Throwable();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable,
                                                       String message) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrows(expectedType, executable, message);
        } else {
            return (T) new Throwable();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable,
                                                       Supplier<String> messageSupplier) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertThrows(expectedType, executable, messageSupplier);
        } else {
            return (T) new Throwable();
        }
    }

    public static void assertDoesNotThrow(Executable executable) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(() -> {
            executable.execute();
            return null;
        }));
        if (forwardInvocations || forwardReturningInvocations) {
            Assertions.assertDoesNotThrow(executable);
        }
    }

    public static void assertDoesNotThrow(Executable executable, String message) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(() -> {
            executable.execute();
            return null;
        }));
        if (forwardInvocations || forwardReturningInvocations) {
            Assertions.assertDoesNotThrow(executable, message);
        }
    }

    public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(supplier));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertDoesNotThrow(supplier);
        } else {
            return null;
        }
    }

    public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, String message) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(supplier));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertDoesNotThrow(supplier, message);
        } else {
            return null;
        }
    }

    public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, Supplier<String> messageSupplier) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(supplier));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertDoesNotThrow(supplier, messageSupplier);
        } else {
            return null;
        }
    }

    // --- assertInstanceOf ----------------------------------------------------

    public static <T> T assertInstanceOf(Class<T> expectedType, Object actualValue) {
        INSTANCE_OF_INVOCATIONS.add(new InstanceOfInvocation<>(expectedType, actualValue));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertInstanceOf(expectedType, actualValue);
        } else {
            return null;
        }
    }

    public static <T> T assertInstanceOf(Class<T> expectedType, Object actualValue, String message) {
        INSTANCE_OF_INVOCATIONS.add(new InstanceOfInvocation<>(expectedType, actualValue));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertInstanceOf(expectedType, actualValue, message);
        } else {
            return null;
        }
    }

    public static <T> T assertInstanceOf(Class<T> expectedType, Object actualValue, Supplier<String> messageSupplier) {
        INSTANCE_OF_INVOCATIONS.add(new InstanceOfInvocation<>(expectedType, actualValue));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertInstanceOf(expectedType, actualValue, messageSupplier);
        } else {
            return null;
        }
    }
}
