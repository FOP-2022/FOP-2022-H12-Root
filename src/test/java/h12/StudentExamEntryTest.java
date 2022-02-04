package h12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentExamEntryTest {

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
        assertEquals("Bad student mark '1,4'", assertThrows(BadStudentMarkException.class,
            () -> new StudentExamEntry("a", "b", 2, "1,4")).getMessage());
        assertEquals("Bad enrollment number '-5'", assertThrows(BadEnrollmentNumberException.class,
            () -> new StudentExamEntry("a", "b", -5, "1,3")).getMessage());
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
        for (String validMark : TestConstants.VALID_MARKS) {
            assertDoesNotThrow(() -> entry.setMark(validMark), "setMark unexpectedly failed for mark " + validMark);
            assertEquals(validMark, entry.getMark());
        }
        assertThrows(BadStudentMarkException.class, () -> entry.setMark("hello"));
        assertThrows(NullPointerException.class, () -> entry.setMark(null));
    }
}
