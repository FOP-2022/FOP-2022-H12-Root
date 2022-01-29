package h12;

public class BadEnrollmentNumberException extends RuntimeException {
    public BadEnrollmentNumberException(int number) {
        super("Bad enrollment number '%d'".formatted(number));
    }
}
