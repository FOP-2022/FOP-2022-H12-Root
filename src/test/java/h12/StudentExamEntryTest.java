package h12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentExamEntryTest {

    static final String[] VALID_MARKS = {
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

    @Test
    void testConstructorsWork() {
        assertDoesNotThrow(() ->
            new StudentExamEntry("a", "b", 1));
        assertDoesNotThrow(() ->
            new StudentExamEntry("a", "b", 1, "1,0"));
    }

    @Test
    void testConstructorsThrow() {
        assertThrows(NullPointerException.class,
            () -> new StudentExamEntry(null, "b", 1, "1,0"));
        assertThrows(NullPointerException.class,
            () -> new StudentExamEntry("b", null, 1, "1,0"));
        assertThrows(NullPointerException.class,
            () -> new StudentExamEntry("a", "b", 1, null));
        assertThrows(BadEnrollmentNumberException.class,
            () -> new StudentExamEntry("a", "b", -5, "1,3"));
        assertThrows(BadStudentMarkException.class,
            () -> new StudentExamEntry("a", "b", 2, "1,4"));

        assertEquals("Bad char ':' at position 0", assertThrows(BadCharException.class, () ->
            new StudentExamEntry(":", "a", 1, "1,0")).getMessage());
        assertEquals("Bad char '!' at position 0", assertThrows(BadCharException.class, () ->
            new StudentExamEntry("!", "a", 1, "1,0")).getMessage());
        assertEquals("Bad char '!' at position 5", assertThrows(BadCharException.class, () ->
            new StudentExamEntry("a", "aaaaa!", 1, "1,0")).getMessage());
    }

    @Test
    void testMarks() {
        StudentExamEntry entry = assertDoesNotThrow(() ->
            new StudentExamEntry("a", "b", 1));
        assertEquals("n/a", entry.getMark());
        for (String validMark : VALID_MARKS) {
            assertDoesNotThrow(() -> entry.setMark(validMark), "setMark unexpectedly failed for mark " + validMark);
            assertEquals(validMark, entry.getMark());
        }
        assertThrows(BadStudentMarkException.class, () -> entry.setMark("hello"));
        assertThrows(NullPointerException.class, () -> entry.setMark(null));
    }
}
