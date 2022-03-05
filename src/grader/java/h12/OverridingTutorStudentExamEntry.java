package h12;

import h12.tablegenerator.SolutionTableGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class OverridingTutorStudentExamEntry extends StudentExamEntry {

    public static @Nullable UnaryOperator<String> FIRST_NAME_TRANSFORMER;
    public static @Nullable UnaryOperator<String> LAST_NAME_TRANSFORMER;
    public static @Nullable IntUnaryOperator ENROLLMENT_NUMBER_TRANSFORMER;
    public static @Nullable UnaryOperator<String> MARK_TRANSFORMER;

    public static void reset() {
        FIRST_NAME_TRANSFORMER = null;
        LAST_NAME_TRANSFORMER = null;
        ENROLLMENT_NUMBER_TRANSFORMER = null;
        MARK_TRANSFORMER = null;
    }

    private final String firstName;
    private final String lastName;
    private final int enrollmentNumber;
    private final String mark;

    public static OverridingTutorStudentExamEntry create(Random random) {
        final String firstName = SolutionTableGenerator.createRandomString(random);
        final String lastName = SolutionTableGenerator.createRandomString(random);
        final int enrollmentNumber = random.nextInt(5, 50);
        final String mark = SolutionTableGenerator.createRandomMark(random);
        return new OverridingTutorStudentExamEntry(firstName, lastName, enrollmentNumber, mark);
    }

    public OverridingTutorStudentExamEntry(final String firstName, final String lastName, final int enrollmentNumber, final String mark) {
        super("a", "b", 1, "n/a");
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentNumber = enrollmentNumber;
        this.mark = mark;
    }

    @Override
    public String getFirstName() {
        if (FIRST_NAME_TRANSFORMER != null) {
            return FIRST_NAME_TRANSFORMER.apply(firstName);
        }
        return firstName;
    }

    @Override
    public String getLastName() {
        if (LAST_NAME_TRANSFORMER != null) {
            return LAST_NAME_TRANSFORMER.apply(lastName);
        }
        return lastName;
    }

    @Override
    public int getEnrollmentNumber() {
        if (ENROLLMENT_NUMBER_TRANSFORMER != null) {
            return ENROLLMENT_NUMBER_TRANSFORMER.applyAsInt(enrollmentNumber);
        }
        return enrollmentNumber;
    }

    @Override
    public String getMark() {
        if (MARK_TRANSFORMER != null) {
            return MARK_TRANSFORMER.apply(mark);
        }
        return mark;
    }

    public String getExpectedOutput() {
        if (mark.equals("n/a")) {
            return "%s:%s:%d:".formatted(firstName, lastName, enrollmentNumber);
        } else {
            return "%s:%s:%d:%s".formatted(firstName, lastName, enrollmentNumber, mark);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final StudentExamEntry that)) return false;
        return getEnrollmentNumber() == that.getEnrollmentNumber()
            && Objects.equals(getFirstName(), that.getFirstName())
            && Objects.equals(getLastName(), that.getLastName())
            && Objects.equals(getMark(), that.getMark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, enrollmentNumber, mark);
    }

    @Override
    public String toString() {
        return "OverridingStudentExamEntry{" +
            "firstName='" + getFirstName() + '\'' +
            ", lastName='" + getLastName() + '\'' +
            ", enrollmentNumber=" + getEnrollmentNumber() +
            ", mark='" + getMark() + '\'' +
            '}';
    }
}
