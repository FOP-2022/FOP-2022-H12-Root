package h12.h1_1;

import h12.BadEnrollmentNumberException;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class BadEnrollmentNumberExceptionTutorTest implements ExceptionTest {

    @Test
    @Override
    public void testMessageCorrect() {
        Class<BadEnrollmentNumberException> type = BadEnrollmentNumberException.class;
        H1_1_Utils.checkException(type);
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
