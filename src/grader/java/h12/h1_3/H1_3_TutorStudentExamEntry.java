package h12.h1_3;

import h12.StudentExamEntry;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

public class H1_3_TutorStudentExamEntry extends StudentExamEntry {

    public static @Nullable Predicate<String> SET_MARK_CONSUMER;

    public H1_3_TutorStudentExamEntry(String firstName, String lastName, int enrollmentNumber) {
        super(firstName, lastName, enrollmentNumber);
    }

    public H1_3_TutorStudentExamEntry(String firstName, String lastName, int enrollmentNumber, String mark) {
        super(firstName, lastName, enrollmentNumber, mark);
    }

    @Override
    public void setMark(String mark) {
        if (SET_MARK_CONSUMER == null || SET_MARK_CONSUMER.test(mark)) {
            super.setMark(mark);
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
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getEnrollmentNumber(), getMark());
    }

    @Override
    public String toString() {
        return "H1_3_TutorStudentExamEntry{" +
            "firstName='" + getFirstName() + '\'' +
            ", lastName='" + getLastName() + '\'' +
            ", enrollmentNumber=" + getEnrollmentNumber() +
            ", mark='" + getMark() + '\'' +
            '}';
    }
}
