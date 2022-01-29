package h12;

import java.util.List;
import java.util.Objects;

class StudentExamEntry {

    private static final char[] BAD_CHARS = {
        ':',
        '!',
    };

    private static final String[] VALID_MARKS = {
        "1,0",
        "1,3",
        "1,7",
        "2,0",
        "2,3",
        "2,7",
        "3,0",
        "3,3",
        "3,7",
        "4,0",
        "5,0",
        "n/a",
    };

    private final String firstName;
    private final String lastName;
    private final int enrollmentNumber;
    private String mark;

    public StudentExamEntry(String firstName, String lastName, int enrollmentNumber) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        if (enrollmentNumber <= 0) {
            throw new BadEnrollmentNumberException(enrollmentNumber);
        }
        this.enrollmentNumber = enrollmentNumber;
        ensureNoBadChar(firstName, lastName);
        mark = VALID_MARKS[VALID_MARKS.length - 1]; // "n/a"
    }

    public StudentExamEntry(String firstName, String lastName, int enrollmentNumber, String mark) {
        this(firstName, lastName, enrollmentNumber);
        setMark(mark);
    }

    private void ensureNoBadChar(String... inputs) {
        for (String input : inputs) {
            for (char c : BAD_CHARS) {
                final int i = input.indexOf(c);
                if (i >= 0) {
                    throw new BadCharException(c, i);
                }
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public String getMark() {
        return mark;
    }

    final void setMark(String mark) {
        Objects.requireNonNull(mark);
        if (!List.of(VALID_MARKS).contains(mark)) {
            throw new BadStudentMarkException(mark);
        }
        this.mark = mark;
    }
}
