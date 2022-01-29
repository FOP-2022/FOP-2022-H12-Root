package h12;

public class BadEnrollmentNumberException extends RuntimeException {
    public BadEnrollmentNumberException(int number) {
        super(String.format("Bad enrollment number '%d'", number));
    }
}
