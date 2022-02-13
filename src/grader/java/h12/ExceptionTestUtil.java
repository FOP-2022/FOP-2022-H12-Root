package h12;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTestUtil {

    static void checkException(Class<? extends Throwable> type) {
        assertEquals(
            Throwable.class,
            assertDoesNotThrow(() -> type.getMethod("getMessage")).getDeclaringClass(),
            type.getName() + " overrides getMessage()"
        );
    }
}
