package h12.h2_2and3;

import h12.StudentExamEntry;
import h12.TableGenerator;
import h12.TableWithTitle;
import h12.TutorUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TableGeneratorSignaturesTutorTest {

    @SuppressWarnings("UnstableApiUsage")
    private Class<?> assertClassExists() {
        final @Nullable TestCycle testCycle = TestCycleResolver.getTestCycle();
        if (testCycle == null) {
            return TableGenerator.class;
        } else {
            return assertDoesNotThrow(() -> testCycle.getClassLoader().loadClass("h12.TableGenerator"),
                "Class h12.TableGenerator does not exist");
        }
    }

    @Test
    public void testCreateEntriesExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertMethod(type, "createEntries", StudentExamEntry[].class, int.class, long.class);
    }

    @Test
    public void testCreateTableExists() {
        Class<?> type = assertClassExists();
        TutorUtils.assertMethod(type, "createTable", TableWithTitle.class, int.class, long.class);
    }
}
