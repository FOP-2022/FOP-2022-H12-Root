package h12.h2_2and3;

import h12.TableGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

@TestForSubmission("h12")
public class TableGeneratorEntriesTutorTest {

    @Test
    public void testCreateEntriesBasic() {
        TableGeneratorCommonTutorTest.testCreateEntriesBasic(TableGenerator::createEntries);
    }

    @Test
    public void testCreateEntriesMid() {
        TableGeneratorCommonTutorTest.testCreateEntriesMid(TableGenerator::createEntries);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testCreateEntriesComplex() {
        TableGeneratorCommonTutorTest.testCreateEntriesComplex(TableGenerator::createEntries);
    }
}
