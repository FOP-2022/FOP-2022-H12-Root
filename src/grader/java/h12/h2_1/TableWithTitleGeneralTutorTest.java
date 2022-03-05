package h12.h2_1;

import h12.StudentExamEntry;
import h12.TableWithTitle;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.SolutionTableGenerator;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TableWithTitleGeneralTutorTest {

    @Test
    public void testFieldsAndGetters() {
        TutorStudentExamTableIO.reset();
        final Random random = new Random(91);
        assertNull(new TableWithTitle(null, new StudentExamEntry[0]).getTitle(),
            "getTitle should return null when title is null");
        for (int i = 0; i < 10; i++) {
            final String title = SolutionTableGenerator.createRandomString(random);
            final StudentExamEntry[] entries = SolutionTableGenerator.createEntries(random.nextInt(4), random);
            final TableWithTitle table = new TableWithTitle(title, entries);
            assertEquals(title, table.getTitle(), "getTitle incorrect");
            assertArrayEquals(entries, table.getEntries(), "getEntries incorrect");
        }
    }
}
