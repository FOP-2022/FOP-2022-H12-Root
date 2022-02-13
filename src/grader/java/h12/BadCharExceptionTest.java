package h12;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BadCharExceptionTest {

    @Test
    public void testMessageCorrect() {
        Class<BadCharException> type = BadCharException.class;
        ExceptionTestUtil.checkException(type);
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
