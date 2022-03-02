package h12.h1_1;

import static org.junit.jupiter.api.Assertions.*;

class H1_1_Utils {

    static void checkException(Class<? extends Throwable> type) {
        assertEquals(
            Throwable.class,
            assertDoesNotThrow(() -> type.getMethod("getMessage")).getDeclaringClass(),
            type.getName() + " overrides getMessage()"
        );
    }
}
