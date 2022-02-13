package h12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadEnrollmentNumberExceptionTest {

    @Test
    public void testMessageCorrect() {
        Class<BadEnrollmentNumberException> type = BadEnrollmentNumberException.class;
        ExceptionTestUtil.checkException(type);
        assertDoesNotThrow(() -> type.getConstructor(int.class),
            type.getName() + " does not have constructor (int)");
        for (int i = 0; i < 10; i++) {
            final int number = -15 * i;
            assertEquals(
                "Bad enrollment number '%d'".formatted(number),
                new BadEnrollmentNumberException(number).getMessage(),
                BadEnrollmentNumberException.class.getName() + " does not have correct message"
            );
        }
    }
}
