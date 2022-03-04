package h12.tableiotest;

import h12.io.TutorBufferedReader;
import h12.io.TutorBufferedWriter;
import h12.tablegenerator.TutorTableGenerator;
import h12.transform.TutorAssumptions;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;

public class StudentExamTableIOTestAssumptionsTutorTest {

    private static boolean[] setInvokeCallbacks(final boolean reader, final boolean writer) {
        final boolean[] invokeCallbacks = new boolean[2];
        TutorIOFactory.CREATE_READER = TutorBufferedReader.FS_IO_TUTOR;
        TutorIOFactory.CREATE_WRITER = TutorBufferedWriter.FS_IO_TUTOR;
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
    ) {
        TutorAssumptions.reset();
        TutorAssumptions.forwardInvocations = true;
        TutorIOFactory.reset();
        final boolean[] invokeCallbacks = setInvokeCallbacks(Boolean.TRUE.equals(reader), Boolean.TRUE.equals(writer));
        assertDoesNotThrow(executable);
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

    public static void testAssume(
        final boolean checkReader,
        final boolean checkWriter,
        final Executable executable
    ) {
        TutorTableGenerator.reset();
        checkAssume(false, null,
            () -> assertThrows(TestAbortedException.class, executable,
                "Test did not abort as expected"));
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isNotCorrect),
            "Expected assumeTrue to be invoked with false");
        TutorTableGenerator.reset();
        checkAssume(null, true, executable);
        assertTrue(TutorAssumptions.streamAllAssumptions().allMatch(TutorAssumptions.Assumption::isCorrect),
            "Expected assumeTrue to be invoked with true");
    }
}
