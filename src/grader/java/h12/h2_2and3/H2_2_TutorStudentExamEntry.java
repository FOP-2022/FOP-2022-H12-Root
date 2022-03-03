package h12.h2_2and3;

import h12.StudentExamEntry;

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
}
