package h12;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BadStudentMarkExceptionTest {

    @Test
    public void testMessageCorrect() {
        Class<BadStudentMarkException> type = BadStudentMarkException.class;
        ExceptionTestUtil.checkException(type);
        assertDoesNotThrow(() -> type.getConstructor(String.class),
            type.getName() + " does not have constructor (String)");
        final Random random = new Random(42);
        for (int i = 0; i < 10; i++) {
            final int length = random.nextInt(3, 9);
            final char[] chars = new char[length];
            for (int j = 0; j < length; j++) {
                chars[j] = (char) random.nextInt('a', 'z' + 1);
            }
            final String mark = new String(chars);
            assertEquals(
                "Bad student mark '%s'".formatted(mark),
                new BadStudentMarkException(mark).getMessage(),
                BadStudentMarkException.class.getName() + " does not have correct message"
            );
        }
    }
}
