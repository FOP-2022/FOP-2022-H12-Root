package h12.h4_1;

import h12.OverridingTutorStudentExamEntry;
import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
import h12.io.TutorBufferedReader;
import h12.io.TutorBufferedWriter;
import h12.io.TutorFileReader;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.TutorTableGenerator;
import h12.tableiotest.FakeFileSystem;
import h12.tableiotest.StudentExamTableIOTestAssumptionsTutorTest;
import h12.tableiotest.TutorIOFactory;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static h12.transform.TutorAssertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission("h12")
public class TestWriteStudentExamTableComplexTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testAssumption() throws Throwable {
        final FakeFileSystem fakeFs = new FakeFileSystem();
        TutorIOFactory.CREATE_READER = fakeFs::createReader;
        TutorIOFactory.CREATE_WRITER = fakeFs::createWriter;
        StudentExamTableIOTestAssumptionsTutorTest.checkAssumeBoth(
            new StudentExamTableIOTest()::testWriteStudentExamTableComplex);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFunction() throws IOException {
        TutorAssumptions.reset();
        TutorAssertions.reset();
        TutorFileReader.createFakeTable();
        OverridingTutorStudentExamEntry.reset();

        TutorTableGenerator.reset();
        TutorTableGenerator.SIZE = size -> assertEquals(50, size, "Expected size 50");
        TutorTableGenerator.USE_SOLUTION = true;
        final List<TableWithTitle> allTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = table -> {
            allTables.add(table);
            return table;
        };

        // io settings
        final TutorStudentExamTableIO.StoreRead storeRead = new TutorStudentExamTableIO.StoreRead();
        TutorStudentExamTableIO.READ = storeRead;
        final TutorStudentExamTableIO.StoreWrite storeWrite = new TutorStudentExamTableIO.StoreWrite();
        TutorStudentExamTableIO.WRITE = storeWrite;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_INFORM_CUSTOM;

        TutorIOFactory.reset();
        TutorIOFactory.READER = true;
        TutorIOFactory.WRITER = true;
        final boolean[] invokeCreate = new boolean[2];
        final List<TutorBufferedReader> readers = new ArrayList<>();
        TutorIOFactory.CREATE_READER = resourceName -> {
            invokeCreate[0] = true;
            final TutorBufferedReader result = TutorBufferedReader.FS_IO_TUTOR.apply(resourceName);
            readers.add(result);
            return result;
        };
        final List<TutorBufferedWriter> writers = new ArrayList<>();
        TutorIOFactory.CREATE_WRITER = resourceName -> {
            invokeCreate[1] = true;
            final TutorBufferedWriter result = TutorBufferedWriter.FS_IO_TUTOR.apply(resourceName);
            writers.add(result);
            return result;
        };

        // important, otherwise methods from StudentExamTableIO are not called
        TutorAssertions.forwardReturningInvocations = true;
        final StudentExamTableIOTest instance = new StudentExamTableIOTest();
        instance.testWriteStudentExamTableComplex();
        assertTrue(invokeCreate[0], "Did not call IOFactory#createReader(String)");
        assertTrue(invokeCreate[1], "Did not call IOFactory#createWriter(String)");
        assertFalse(allTables.isEmpty(), "Did not call TableGenerator#createTable");

        assertTrue(storeWrite.checkForAll(w -> {
            final Writer wr = w.writer();
            return wr instanceof TutorBufferedWriter && writers.contains(wr);
        }), "Did not use result from IOFactory#createWriter(String)");

        assertTrue(storeRead.readers.stream().allMatch(r -> r instanceof TutorBufferedReader && readers.contains(r)),
            "Did not use result from IOFactory#createReader(String)");

        // ensure that all created tables were used
        // in StudentExamTableIO#writeStudentExamTable(Writer, StudentExamTableIO[], String)
        for (TableWithTitle table : allTables) {
            assertTrue(
                storeWrite.writeData0List.stream()
                    .anyMatch(w -> Arrays.equals(w.entries(), table.getEntries()) && w.title().equals(table.getTitle())),
                "Did not use result from TableGenerator#createTable in writeStudentExamTable");
        }

        TutorStudentExamTableIO.reset();
        TutorTableGenerator.reset();
        TutorAssumptions.reset();
        TutorAssertions.reset();
        TutorIOFactory.reset();
        TutorIOFactory.READER = true;
        TutorIOFactory.WRITER = true;

        final FakeFileSystem fakeFs0 = new FakeFileSystem();
        TutorIOFactory.CREATE_READER = fakeFs0::createReader;
        TutorIOFactory.CREATE_WRITER = fakeFs0::createWriter;
        TutorAssertions.forwardInvocations = true;
        final List<TableWithTitle> createdTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = table -> {
            createdTables.add(table);
            return table;
        };
        final boolean original = checkIfOriginalWorks(instance);
        assertEquals(1, createdTables.size(),
            "Incorrect number of calls to TableGenerator#createTable");
        TutorTableGenerator.USE_SOLUTION = !original;
        TutorStudentExamTableIO.reset();
        TutorStudentExamTableIO.EXECUTION_TYPE = original
            ? TutorStudentExamTableIO.ExecutionType.USE_ORIGINAL
            : TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;

        createdTables.clear();

        // break title and make sure test reacts correctly

        final FakeFileSystem fakeFs1 = new FakeFileSystem(tableString -> {
            final String[] split = tableString.split("\n");
            split[0] = split[0] + "foo";
            return String.join("\n", split);
        });

        TutorIOFactory.CREATE_READER = fakeFs1::createReader;
        TutorIOFactory.CREATE_WRITER = fakeFs1::createWriter;

        assertThrows(AssertionFailedError.class, () -> invoke(instance, original),
            "Incorrect table title did not lead to failed test");
        assertEquals(1, createdTables.size(),
            "Incorrect number of calls to TableGenerator#createTable");

        for (int i = 0; i < 50; i++) {
            final int finalI = i;
            final FakeFileSystem fakeFs2 = new FakeFileSystem(tableString -> {
                // tableSize is 50 so this is ok
                final String[] split = tableString.split("\n");
                split[finalI] = split[finalI] + "foo";
                return String.join("\n", split);
            });

            TutorIOFactory.CREATE_READER = fakeFs2::createReader;
            TutorIOFactory.CREATE_WRITER = fakeFs2::createWriter;

            assertThrows(AssertionFailedError.class, () -> invoke(instance, original),
                "Incorrect entry " + i + " did not lead to failed test");
        }
    }

    private void invoke(final StudentExamTableIOTest instance, final boolean original) throws IOException {
        if (original) {
            invokeOriginal(instance);
        } else {
            invokeSolution(instance);
        }
    }

    private void invokeOriginal(final StudentExamTableIOTest instance) throws IOException {
        TutorTableGenerator.USE_SOLUTION = false;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_ORIGINAL;
        instance.testWriteStudentExamTableComplex();
    }

    private void invokeSolution(final StudentExamTableIOTest instance) throws IOException {
        TutorTableGenerator.USE_SOLUTION = true;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;
        instance.testWriteStudentExamTableComplex();
    }

    private boolean checkIfOriginalWorks(final StudentExamTableIOTest instance) throws IOException {
        try {
            invokeOriginal(instance);
            return true;
        } catch (Exception | AssertionFailedError e) {
            // try again with tutor solution for createTable and read/write
            invokeSolution(instance);
            return false;
        }
    }
}
