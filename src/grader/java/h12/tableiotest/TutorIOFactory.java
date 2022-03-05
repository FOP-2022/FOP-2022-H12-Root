package h12.tableiotest;

import h12.IOFactory;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class TutorIOFactory implements IOFactory {

    public static @Nullable Function<String, ? extends BufferedReader> CREATE_READER;
    public static @Nullable Function<String, ? extends BufferedWriter> CREATE_WRITER;
    public static @Nullable Runnable SUPPORTS_READER;
    public static @Nullable Runnable SUPPORTS_WRITER;
    public static boolean READER;
    public static boolean WRITER;

    public static void reset() {
        CREATE_READER = null;
        CREATE_WRITER = null;
        SUPPORTS_READER = null;
        SUPPORTS_WRITER = null;
        READER = false;
        WRITER = false;
    }

    @Override
    public BufferedReader createReader(String resourceName) {
        if (CREATE_READER == null) {
            return fail("IOFactory#createReader(String) invoked illegally");
        }
        return CREATE_READER.apply(resourceName);
    }

    @Override
    public BufferedWriter createWriter(String resourceName) {
        if (CREATE_WRITER == null) {
            return fail("IOFactory#createWriter(String) invoked illegally");
        }
        return CREATE_WRITER.apply(resourceName);
    }

    @Override
    public boolean supportsReader() {
        if (SUPPORTS_READER != null) {
            SUPPORTS_READER.run();
        }
        return READER;
    }

    @Override
    public boolean supportsWriter() {
        if (SUPPORTS_WRITER != null) {
            SUPPORTS_WRITER.run();
        }
        return WRITER;
    }
}
