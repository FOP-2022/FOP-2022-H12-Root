package h12.h2_2and3;

import h12.StudentExamEntry;

import java.util.Objects;

public class H2_2_TutorStudentExamEntry extends StudentExamEntry {

    public static boolean INVOKE_3;
    public static boolean INVOKE_4;

    public static void reset() {
        INVOKE_3 = false;
        INVOKE_4 = false;
    }

    public H2_2_TutorStudentExamEntry(final String firstName, final String lastName, final int enrollmentNumber) {
        super(firstName, lastName, enrollmentNumber);
        INVOKE_3 = true;
    }

    public H2_2_TutorStudentExamEntry(final String firstName, final String lastName, final int enrollmentNumber, final String mark) {
        super(firstName, lastName, enrollmentNumber, mark);
        INVOKE_4 = true;
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
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getEnrollmentNumber(), getMark());
    }

    @Override
    public String toString() {
        return "H2_2_TutorStudentExamEntry{" +
            "firstName='" + getFirstName() + '\'' +
            ", lastName='" + getLastName() + '\'' +
            ", enrollmentNumber=" + getEnrollmentNumber() +
            ", mark='" + getMark() + '\'' +
            '}';
    }
}
