package h12.h1_1;

import h12.BadCharException;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class BadCharExceptionTutorTest implements ExceptionTest {

    @Test
    @Override
    public void testMessageCorrect() {
        Class<BadCharException> type = BadCharException.class;
        H1_1_Utils.checkException(type);
        assertDoesNotThrow(() -> type.getConstructor(char.class, int.class),
            type.getName() + " does not have constructor (char, int)");
        final Random rand = new Random(500);
        for (int i = 0; i < 26; i++) {
            final char character = (char) (i + 'a');
            final int index = rand.nextInt();
            System.out.println(index);
            assertEquals(
                "Bad char '%s' at position %d".formatted(character, index),
                new BadCharException(character, index).getMessage(),
                BadCharException.class.getName() + " does not have correct message"
            );
        }
    }
}
