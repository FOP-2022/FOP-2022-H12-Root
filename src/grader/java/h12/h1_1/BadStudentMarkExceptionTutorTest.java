package h12.h1_1;

import h12.BadStudentMarkException;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class BadStudentMarkExceptionTutorTest implements ExceptionTest {

    @Test
    @Override
    public void testMessageCorrect() {
        Class<BadStudentMarkException> type = BadStudentMarkException.class;
        H1_1_Utils.checkException(type);
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
