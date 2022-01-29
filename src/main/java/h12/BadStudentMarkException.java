package h12;

public class BadStudentMarkException extends RuntimeException {
    public BadStudentMarkException(String message) {
        super(String.format("Bad student mark '%s'", message));
    }
}
