package h12;

import java.util.Random;

public class SolutionTableGenerator {

    public static final char[] A_Z = new char['z' - 'a' + 1];

    static {
        for (char i = 'a'; i <= 'z'; i++) {
            A_Z[i - 'a'] = i;
        }
    }

    public static final String[] VALID_MARKS = {
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

    public static String createRandomMark(Random random) {
        return VALID_MARKS[random.nextInt(VALID_MARKS.length)];
    }

    public static String createRandomString(Random random) {
        final int size = random.nextInt(5, 26);
        final char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = A_Z[random.nextInt('z' - 'a')];
        }
        return new String(chars);
    }

    public static StudentExamEntry[] createEntries(int size, long seed) {
        return createEntries(size, new Random(seed));
    }

    public static StudentExamEntry[] createEntries(int size, Random random) {
        final StudentExamEntry[] result = new StudentExamEntry[size];
        final String[] marks = VALID_MARKS;
        for (int i = 0; i < size; i++) {
            final String firstName = createRandomString(random);
            final String lastName = createRandomString(random);
            final int enrollmentNumber = random.nextInt(1000, 10000000);
            final String mark = marks[random.nextInt(marks.length)];
            result[i] = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
        }
        return result;
    }

    public static TableWithTitle createTable(int size, long seed) {
        final Random random = new Random(seed);
        return createTable(size, random);
    }

    public static TableWithTitle createTable(int size, Random random) {
        final StudentExamEntry[] entries = createEntries(size, random);
        return new TableWithTitle(createRandomString(random), entries);
    }
}
