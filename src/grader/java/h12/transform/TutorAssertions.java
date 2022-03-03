package h12.transform;

import org.apiguardian.api.API;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.apiguardian.api.API.Status.STABLE;

@SuppressWarnings("unused")
public class TutorAssertions {

    public record FailInvocation(@Nullable String message, @Nullable Throwable throwable) {}

    public record NullInvocation(@Nullable Object actual) {}

    public record NotNullInvocation(@Nullable Object actual) {}

    public record EqualsInvocation(Object expected, Object actual) {}

    public record NotEqualsInvocation(Object expected, Object actual) {}

    public record ArrayEqualsInvocation(Object expected, Object actual) {}

    public record ArrayNotEqualsInvocation(Object expected, Object actual) {}

    public record ThrowsInvocation<T extends Throwable>(Class<T> expectedType, Executable executable) {}

    public record DoesNotThrowInvocation<T>(ThrowingSupplier<T> supplier) {}

    public static final List<FailInvocation> FAIL_INVOCATIONS = new ArrayList<>();
    public static final List<NullInvocation> NULL_INVOCATIONS = new ArrayList<>();
    public static final List<NotNullInvocation> NOT_NULL_INVOCATIONS = new ArrayList<>();
    public static final List<EqualsInvocation> EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<NotEqualsInvocation> NOT_EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<ArrayEqualsInvocation> ARRAY_EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<ArrayNotEqualsInvocation> ARRAY_NOT_EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<ThrowsInvocation<?>> THROWS_INVOCATIONS = new ArrayList<>();
    public static final List<DoesNotThrowInvocation<?>> DOES_NOT_THROW_INVOCATIONS = new ArrayList<>();
    public static boolean forwardInvocations = false;
    public static boolean forwardReturningInvocations = false;

    public static void reset() {
        FAIL_INVOCATIONS.clear();
        NULL_INVOCATIONS.clear();
        NOT_NULL_INVOCATIONS.clear();
        EQUALS_INVOCATIONS.clear();
        NOT_EQUALS_INVOCATIONS.clear();
        ARRAY_EQUALS_INVOCATIONS.clear();
        ARRAY_NOT_EQUALS_INVOCATIONS.clear();
        THROWS_INVOCATIONS.clear();
        DOES_NOT_THROW_INVOCATIONS.clear();
        forwardInvocations = false;
        forwardReturningInvocations = false;
    }

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

    @API(status = STABLE, since = "5.4")
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

    @API(status = STABLE, since = "5.4")
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

    @API(status = STABLE, since = "5.4")
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

    @API(status = STABLE, since = "5.4")
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

    @API(status = STABLE, since = "5.4")
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

    @API(status = STABLE, since = "5.4")
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

    public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(supplier));
        if (forwardInvocations || forwardReturningInvocations) {
            return Assertions.assertDoesNotThrow(supplier);
        } else {
            return null;
        }
    }

    public static void assertDoesNotThrow(Executable executable) {
        assertDoesNotThrow(() -> {
            executable.execute();
            return null;
        });
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
}
