package h12;

public class BadStudentMarkException extends RuntimeException {
    public BadStudentMarkException(String message) {
        super("Bad student mark '%s'".formatted(message));
    }
}
