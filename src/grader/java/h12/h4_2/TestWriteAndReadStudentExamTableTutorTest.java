package h12.h4_2;

import h12.OverridingTutorStudentExamEntry;
import h12.StudentExamTableIOTest;
import h12.TableWithTitle;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static h12.transform.TutorAssertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DuplicatedCode")
@TestForSubmission("h12")
public class TestWriteAndReadStudentExamTableTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testAssumption() throws Throwable {
        final FakeFileSystem fakeFs = new FakeFileSystem();
        TutorIOFactory.CREATE_READER = fakeFs::createReader;
        TutorIOFactory.CREATE_WRITER = fakeFs::createWriter;
        StudentExamTableIOTestAssumptionsTutorTest.checkAssumeBoth(
            new StudentExamTableIOTest()::testWriteAndReadStudentExamTable);
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testBasic() throws IOException {
        TutorAssumptions.reset();
        TutorAssertions.reset();
        TutorFileReader.createFakeTable();
        OverridingTutorStudentExamEntry.reset();

        TutorTableGenerator.reset();
        TutorTableGenerator.SIZE = size -> assertEquals(200, size, "Expected size 200");
        TutorTableGenerator.USE_SOLUTION = true;
        final List<TableWithTitle> allTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = table -> {
            allTables.add(table);
            return table;
        };

        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;

        TutorIOFactory.reset();
        TutorIOFactory.READER = true;
        TutorIOFactory.WRITER = true;
        final boolean[] invokeCreate = new boolean[2];
        final FakeFileSystem fakeFs0 = new FakeFileSystem();
        TutorIOFactory.CREATE_READER = resourceName -> {
            invokeCreate[0] = true;
            return fakeFs0.createReader(resourceName);
        };
        TutorIOFactory.CREATE_WRITER = resourceName -> {
            invokeCreate[1] = true;
            return fakeFs0.createWriter(resourceName);
        };

        // important, otherwise methods from StudentExamTableIO are not called
        TutorAssertions.forwardInvocations = true;
        TutorTableGenerator.USE_SOLUTION = true;
        final StudentExamTableIOTest instance = new StudentExamTableIOTest();
        instance.testWriteAndReadStudentExamTable();
        assertTrue(invokeCreate[0], "Did not call IOFactory#createReader(String)");
        assertTrue(invokeCreate[1], "Did not call IOFactory#createWriter(String)");
        assertEquals(100, allTables.size(),
            "Did not call TableGenerator#createTable the correct amount of times");
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testComplex() throws IOException {
        TutorAssumptions.reset();
        TutorAssertions.reset();
        TutorFileReader.createFakeTable();
        OverridingTutorStudentExamEntry.reset();

        TutorTableGenerator.reset();
        TutorTableGenerator.SIZE = size -> assertEquals(200, size, "Expected size 200");
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
        final List<BufferedReader> readers = new ArrayList<>();
        final FakeFileSystem fakeFs0 = new FakeFileSystem();
        TutorIOFactory.CREATE_READER = resourceName -> {
            invokeCreate[0] = true;
            final BufferedReader result = fakeFs0.createReader(resourceName);
            readers.add(result);
            return result;
        };
        final List<BufferedWriter> writers = new ArrayList<>();
        TutorIOFactory.CREATE_WRITER = resourceName -> {
            invokeCreate[1] = true;
            final BufferedWriter result = fakeFs0.createWriter(resourceName);
            writers.add(result);
            return result;
        };

        // important, otherwise methods from StudentExamTableIO are not called
        TutorAssertions.forwardInvocations = true;
        TutorTableGenerator.USE_SOLUTION = true;
        final StudentExamTableIOTest instance = new StudentExamTableIOTest();
        instance.testWriteAndReadStudentExamTable();
        assertTrue(invokeCreate[0], "Did not call IOFactory#createReader(String)");
        assertTrue(invokeCreate[1], "Did not call IOFactory#createWriter(String)");
        assertEquals(100, allTables.size(),
            "Incorrect number of calls to TableGenerator#createTable");

        final List<TableWithTitle> createdTables = new ArrayList<>();
        TutorTableGenerator.CREATE_TABLE = table -> {
            createdTables.add(table);
            return table;
        };
        final boolean original = checkIfOriginalWorks(instance);
        TutorTableGenerator.USE_SOLUTION = !original;
        TutorStudentExamTableIO.reset();
        TutorStudentExamTableIO.EXECUTION_TYPE = original
            ? TutorStudentExamTableIO.ExecutionType.USE_ORIGINAL
            : TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;

        // break title and make sure test reacts correctly

        final FakeFileSystem fakeFs1 = new FakeFileSystem(tableString -> {
            final String[] split = tableString.split("\n");
            if (split[0].startsWith("!")) {
                split[0] = split[0] + "foo";
            }
            return String.join("\n", split);
        });

        TutorIOFactory.CREATE_READER = fakeFs1::createReader;
        TutorIOFactory.CREATE_WRITER = fakeFs1::createWriter;

        assertThrows(AssertionFailedError.class, () -> invoke(instance, original),
            "Incorrect table title did not lead to failed test");

        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            final int breakIndex = random.nextInt(5, 200);
            final FakeFileSystem fakeFs2 = new FakeFileSystem(tableString -> {
                final String[] split = tableString.split("\n");
                split[breakIndex] = split[breakIndex] + "foo";
                return String.join("\n", split);
            });

            TutorIOFactory.CREATE_READER = fakeFs2::createReader;
            TutorIOFactory.CREATE_WRITER = fakeFs2::createWriter;

            assertThrows(AssertionFailedError.class, () -> invoke(instance, original),
                "Incorrect entry " + breakIndex + " did not lead to failed test");
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
        instance.testWriteAndReadStudentExamTable();
    }

    private void invokeSolution(final StudentExamTableIOTest instance) throws IOException {
        TutorTableGenerator.USE_SOLUTION = true;
        TutorStudentExamTableIO.EXECUTION_TYPE = TutorStudentExamTableIO.ExecutionType.USE_SOLUTION_NO_INFORM;
        instance.testWriteAndReadStudentExamTable();
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
