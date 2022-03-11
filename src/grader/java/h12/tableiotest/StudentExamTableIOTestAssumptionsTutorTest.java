package h12.tableiotest;

import h12.io.TutorBufferedReader;
import h12.io.TutorBufferedWriter;
import h12.studentexamtableio.TutorStudentExamTableIO;
import h12.tablegenerator.TutorTableGenerator;
import h12.transform.TutorAssertions;
import h12.transform.TutorAssumptions;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;

public class StudentExamTableIOTestAssumptionsTutorTest {

    private static boolean[] setInvokeCallbacks(final boolean reader, final boolean writer) {
        final boolean[] invokeCallbacks = new boolean[2];
        TutorIOFactory.SUPPORTS_READER = () -> invokeCallbacks[0] = true;
        TutorIOFactory.SUPPORTS_WRITER = () -> invokeCallbacks[1] = true;
        TutorIOFactory.READER = reader;
        TutorIOFactory.WRITER = writer;
        return invokeCallbacks;
    }

    private static void checkAssume(
        final @Nullable Boolean reader,
        final @Nullable Boolean writer,
        final Executable executable
    ) throws Throwable {
        TutorTableGenerator.reset();
        TutorAssumptions.reset();
        TutorStudentExamTableIO.reset();
        final boolean[] invokeCallbacks = setInvokeCallbacks(Boolean.TRUE.equals(reader), Boolean.TRUE.equals(writer));
        if (Boolean.FALSE.equals(reader) || Boolean.FALSE.equals(writer)) {
            TutorAssumptions.forwardInvocations = true;
            assertThrows(TestAbortedException.class, executable,
                "Test did not abort as expected when ioFactory read/write not supported");
        }
        TutorAssumptions.reset();
        TutorAssertions.reset();
        executable.execute();
        if (reader == null) {
            assertFalse(invokeCallbacks[0], "Used IOFactory#supportsReader()");
        } else {
            assertTrue(invokeCallbacks[0], "Did not use IOFactory#supportsReader()");
        }
        if (writer == null) {
            assertFalse(invokeCallbacks[1], "Used IOFactory#supportsWriter()");
        } else {
            assertTrue(invokeCallbacks[1], "Did not use IOFactory#supportsWriter()");
        }
    }

    public static void checkAssumeReader(final Executable executable) throws Throwable {
        checkAssume(false, null, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false from IOFactory#supportsReader()");

        checkAssume(true, null, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true from IOFactory#supportsReader()");
    }

    public static void checkAssumeWriter(final Executable executable) throws Throwable {
        checkAssume(null, false, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false from IOFactory#supportsWriter()");

        checkAssume(null, true, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true from IOFactory#supportsWriter()");
    }

    public static void checkAssumeBoth(final Executable executable) throws Throwable {
        checkAssume(false, false, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false from IOFactory#supportsReader() or supportsWriter()");

        checkAssume(false, true, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().anyMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false from IOFactory#supportsReader()");
        assertTrue(TutorAssumptions.streamAllAssumptions().anyMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true from IOFactory#supportsWriter()");

        checkAssume(true, false, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().anyMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false from IOFactory#supportsWriter()");
        assertTrue(TutorAssumptions.streamAllAssumptions().anyMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true from IOFactory#supportsReader()");

        checkAssume(true, true, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true from IOFactory#supportsReader() and supportsWriter()");
    }
}
