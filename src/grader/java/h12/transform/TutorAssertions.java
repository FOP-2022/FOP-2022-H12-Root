package h12.transform;

import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.apiguardian.api.API.Status.STABLE;

@SuppressWarnings("unused")
public class TutorAssertions {

    public static final List<EqualsInvocation> EQUALS_INVOCATIONS = new ArrayList<>();
    public static final List<ThrowsInvocation<?>> THROWS_INVOCATIONS = new ArrayList<>();
    public static final List<DoesNotThrowInvocation<?>> DOES_NOT_THROW_INVOCATIONS = new ArrayList<>();
    public static boolean forwardInvocation = false;

    public static void reset() {
        EQUALS_INVOCATIONS.clear();
        THROWS_INVOCATIONS.clear();
        DOES_NOT_THROW_INVOCATIONS.clear();
    }

    public static void assertEquals(Object expected, Object actual) {
        EQUALS_INVOCATIONS.add(new EqualsInvocation(expected, actual));
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        assertEquals(expected, actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Object expected, Object actual, Supplier<String> messageSupplier) {
        assertEquals(expected, actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(short expected, Short actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Short expected, short actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Short expected, Short actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(short expected, short actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(short expected, Short actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Short expected, short actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Short expected, Short actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(short expected, short actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(short expected, Short actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Short expected, short actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Short expected, Short actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(byte expected, byte actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(byte expected, Byte actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Byte expected, byte actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Byte expected, Byte actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(byte expected, byte actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(byte expected, Byte actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Byte expected, byte actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Byte expected, Byte actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(byte expected, byte actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(byte expected, Byte actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Byte expected, byte actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Byte expected, Byte actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual, messageSupplier);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, Integer actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Integer expected, int actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Integer expected, Integer actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, int actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(int expected, Integer actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Integer expected, int actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Integer expected, Integer actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(int expected, int actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(int expected, Integer actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Integer expected, int actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Integer expected, Integer actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(long expected, Long actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Long expected, long actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Long expected, Long actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(long expected, long actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(long expected, Long actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Long expected, long actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Long expected, Long actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(long expected, long actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(long expected, Long actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Long expected, long actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Long expected, Long actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, float actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(float expected, Float actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Float expected, float actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Float expected, Float actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(float expected, float actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(float expected, Float actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Float expected, float actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Float expected, Float actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(float expected, float actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, Float actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Float expected, float actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Float expected, Float actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta);
        }
    }

    public static void assertEquals(float expected, float actual, float delta, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta, message);
        }
    }

    public static void assertEquals(float expected, float actual, float delta, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertEquals(double expected, double actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(double expected, Double actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Double expected, double actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Double expected, Double actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(double expected, double actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(double expected, Double actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Double expected, double actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Double expected, Double actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(double expected, double actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(double expected, Double actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Double expected, double actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    @API(status = STABLE, since = "5.4")
    public static void assertEquals(Double expected, Double actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta);
        }
    }

    public static void assertEquals(double expected, double actual, double delta, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta, message);
        }
    }

    public static void assertEquals(double expected, double actual, double delta, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, delta, messageSupplier);
        }
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, Character actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Character expected, char actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(Character expected, Character actual) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, char actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual);
        }
    }

    public static void assertEquals(char expected, Character actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Character expected, char actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(Character expected, Character actual, String message) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public static void assertEquals(char expected, char actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(char expected, Character actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Character expected, char actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    public static void assertEquals(Character expected, Character actual, Supplier<String> messageSupplier) {
        assertEquals(expected, (Object) actual);
        if (forwardInvocation) {
            Assertions.assertEquals(expected, actual, messageSupplier);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
        THROWS_INVOCATIONS.add(new ThrowsInvocation<>(expectedType, executable));
        return (T) new Throwable();
    }

    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable,
                                                       String message) {
        return assertThrows(expectedType, executable);
    }

    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable,
                                                       Supplier<String> messageSupplier) {
        return assertThrows(expectedType, executable);
    }

    @SuppressWarnings("unchecked")
    public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier) {
        DOES_NOT_THROW_INVOCATIONS.add(new DoesNotThrowInvocation<>(supplier));
        return (T) new Object();
    }

    public static void assertDoesNotThrow(Executable executable) {
        assertDoesNotThrow(() -> {
            executable.execute();
            return null;
        });
    }

    public static void assertDoesNotThrow(Executable executable, String message) {
        assertDoesNotThrow(executable);
    }

    public record EqualsInvocation(Object expected, Object actual) {}

    public record ThrowsInvocation<T extends Throwable>(Class<T> expectedType, Executable executable) {}

    public record DoesNotThrowInvocation<T>(ThrowingSupplier<T> supplier) {}
}
