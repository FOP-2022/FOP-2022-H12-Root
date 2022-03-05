package h12;

import h12.tablegenerator.SolutionTableGenerator;

import java.util.Random;

public class OverridingTutorStudentExamEntry extends StudentExamEntry {
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
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getEnrollmentNumber() {
        return enrollmentNumber;
    }

    @Override
    public String getMark() {
        return mark;
    }

    public String getExpectedOutput() {
        if (mark.equals("n/a")) {
            return "%s:%s:%d:".formatted(firstName, lastName, enrollmentNumber);
        } else {
            return "%s:%s:%d:%s".formatted(firstName, lastName, enrollmentNumber, mark);
        }
    }
}
