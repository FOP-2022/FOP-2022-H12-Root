package h12;

public class BadStudentMarkException extends RuntimeException {
    public BadStudentMarkException(String mark) {
        super(String.format("Bad student mark '%s'", mark));
    }
}
