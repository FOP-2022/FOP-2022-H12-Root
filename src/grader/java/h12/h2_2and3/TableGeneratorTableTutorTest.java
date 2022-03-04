package h12.h2_2and3;

import h12.TableGenerator;
import h12.TableWithTitle;
import h12.tablegenerator.TutorTableGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class TableGeneratorTableTutorTest {

    private static void testCreateTable(Consumer<TableGeneratorCommonTutorTest.Generator> generatorConsumer) {
        // yes, this is 3 levels of lambda
        Runnable test = () -> generatorConsumer.accept((size, seed) -> {
            final TableWithTitle t1 = TableGenerator.createTable(size, seed);
            final TableWithTitle t2 = TableGenerator.createTable(size, seed + 1);
            assertNotNull(t1.getTitle(), "title should not be null");
            assertNotNull(t2.getTitle(), "title should not be null");
            TableGeneratorCommonTutorTest.checkString(t1.getTitle(), "title");
            TableGeneratorCommonTutorTest.checkString(t2.getTitle(), "title");
            assertNotEquals(t1.getTitle(), t2.getTitle(),
                "Tables with different seeds should have different titles");
            return t1.getEntries();
        });
        for (int i = 0; i < 10; i++) {
            TutorTableGenerator.USE_SOLUTION = false;
            try {
                test.run();
            } catch (Exception | AssertionFailedError ignored) {
                // try again with tutor solution for createEntries
                TutorTableGenerator.USE_SOLUTION = true;
                test.run();
            }
        }
    }

    @Test
    public void testCreateTableBasic() {
        testCreateTable(TableGeneratorCommonTutorTest::testCreateEntriesBasic);
    }

    @Test
    public void testCreateTableMid() {
        testCreateTable(TableGeneratorCommonTutorTest::testCreateEntriesMid);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testCreateTableComplex() {
        testCreateTable(TableGeneratorCommonTutorTest::testCreateEntriesBasic);
    }
}
