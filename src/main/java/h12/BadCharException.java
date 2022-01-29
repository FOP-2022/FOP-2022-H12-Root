package h12;

public class BadCharException extends RuntimeException {
    public BadCharException(char character, int index) {
        super("Bad char '%s' at position %d".formatted(character, index));
    }
}
