package h12.h1_3;

import h12.StudentExamEntry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SetMark_TutorStudentExamEntry extends StudentExamEntry {

    public static @Nullable Predicate<String> SET_MARK_CONSUMER;

    public SetMark_TutorStudentExamEntry(String firstName, String lastName, int enrollmentNumber) {
        super(firstName, lastName, enrollmentNumber);
    }

    public SetMark_TutorStudentExamEntry(String firstName, String lastName, int enrollmentNumber, String mark) {
        super(firstName, lastName, enrollmentNumber, mark);
    }

    @Override
    public void setMark(String mark) {
        if (SET_MARK_CONSUMER == null || SET_MARK_CONSUMER.test(mark)) {
            super.setMark(mark);
        }
    }
}
