package h12;

public class BadCharException extends RuntimeException {
    public BadCharException(char character, int index) {
        super(String.format("Bad char '%s' at position %d", character, index));
    }
}
