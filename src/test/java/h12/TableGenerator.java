package h12;

import java.util.Random;

class TableGenerator {

    private static String createRandomString(Random random) {
        final int size = random.nextInt(5, 26);
        final char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = TestConstants.A_Z[random.nextInt('z' - 'a')];
        }
        return new String(chars);
    }

    static StudentExamEntry[] createEntries(int size, long seed) {
        return createEntries(size, new Random(seed));
    }

    private static StudentExamEntry[] createEntries(int size, Random random) {
        final StudentExamEntry[] result = new StudentExamEntry[size];
        final String[] marks = StudentExamEntry.VALID_MARKS;
        for (int i = 0; i < size; i++) {
            final String firstName = createRandomString(random);
            final String lastName = createRandomString(random);
            final int enrollmentNumber = random.nextInt(1000, 10000000);
            final String mark = marks[random.nextInt(marks.length)];
            result[i] = new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
        }
        return result;
    }

    static TableWithTitle createTable(int size, long seed) {
        final Random random = new Random(seed);
        final StudentExamEntry[] entries = createEntries(size, random);
        return new TableWithTitle(createRandomString(random), entries);
    }
}
