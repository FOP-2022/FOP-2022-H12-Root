package h12.h2_1;

import h12.StudentExamEntry;
import h12.TableWithTitle;
import h12.TutorUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TableWithTitleSignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return TableWithTitle.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.TableWithTitle"),
                "Class h12.TableWithTitle does not exist");
        }
    }

    @Test
    public void testAttributesExist() {
        Class<?> type = assertClassExists();
        TutorUtils.assertField(type, String.class, true, "title");
        TutorUtils.assertField(type, StudentExamEntry[].class, true, "entries");
    }

    @Test
    public void testConstructorExists() {
        Class<?> type = assertClassExists();
        assertDoesNotThrow(() -> type.getConstructor(String.class, StudentExamEntry[].class),
            "Constructor TableWithTitle(String, StudentExamEntry[]) does not exist");
    }

    @Test
    public void testGettersExist() {
        Class<?> type = assertClassExists();
        TutorUtils.assertGetter(type, "getTitle", String.class);
        TutorUtils.assertGetter(type, "getEntries", StudentExamEntry[].class);
    }
}
