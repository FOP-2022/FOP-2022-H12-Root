package h12.h2_2and3;

import h12.StudentExamEntry;
import h12.tablegenerator.SolutionTableGenerator;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TableGeneratorCommonTutorTest {

    @FunctionalInterface
    interface Generator {
        StudentExamEntry[] generate(int size, long seed);
    }

    public static void testCreateEntriesBasic(Generator generator) {
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final StudentExamEntry[] entries = generator.generate(tableSize, i);
            assertEquals(tableSize, entries.length);
            for (int j = 0; j < entries.length; j++) {
                assertNotNull(entries[j], "entry at index " + j + " is null");
            }
        }
    }

    public static void testCreateEntriesMid(Generator generator) {
        for (int i = 0; i < 10; i++) {
            final int tableSize = i * 4;
            final StudentExamEntry[] entries = generator.generate(tableSize, i);
            assertEquals(tableSize, entries.length);
            for (int j = 0; j < entries.length; j++) {
                final StudentExamEntry entry = entries[j];
                assertNotNull(entry, "entry at index " + j + " is null");
                checkString(entry.getFirstName(), "firstName");
                checkString(entry.getLastName(), "lastName");
                checkEnrollmentNumber(entry.getEnrollmentNumber());
                checkMark(entry.getMark());
            }
        }
    }

    public static void testCreateEntriesComplex(Generator generator) {
        final Random random = new Random(12);
        for (int i = 0; i < 10; i++) {
            H2_2_TutorStudentExamEntry.reset();
            final int tableSize = i * 3;
            final int seed = random.nextInt();
            final StudentExamEntry[] e1 = generator.generate(tableSize, seed);
            // constructor may not be called if producing zero-length array
            assertTrue(tableSize == 0 || H2_2_TutorStudentExamEntry.INVOKE_4, "Did not invoke constructor with 4 arguments");
            assertFalse(H2_2_TutorStudentExamEntry.INVOKE_3, "Invoked constructor with 3 arguments");
            assertEquals(tableSize, e1.length, "Table length incorrect");
            final StudentExamEntry[] e2 = generator.generate(tableSize, seed);
            assertArrayEquals(e1, e2, "Two invocations of createEntries with the same input should produce the same result");
            final StudentExamEntry[] e3 = generator.generate(tableSize, seed + 1);
            assertEquals(e1.length, e3.length, "Tables generated with the same length should have the same length");
            for (int j = 0; j < tableSize; j++) {
                assertNotEquals(e1[j], e3[j], "Tables generated with different seed values should have different elements");
            }
        }
    }

    public static void checkString(String string, String name) {
        final int length = string.length();
        assertTrue(length >= 5 && length <= 25,
            name + " length is not in range [5, 25]. Got " + length);
    }

    private static void checkMark(String mark) {
        assertTrue(List.of(SolutionTableGenerator.VALID_MARKS).contains(mark),
            "invalid mark " + mark);
    }

    private static void checkEnrollmentNumber(int number) {
        assertTrue(number >= 1000 && number <= 9999999,
            "enrollmentNumber length is not in range [1000, 9999999]. Got " + number);
    }
}
