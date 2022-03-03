package h12;

import org.jetbrains.annotations.Nullable;
import org.opentest4j.AssertionFailedError;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class TutorIOFactory implements IOFactory {

    public static @Nullable Function<String, BufferedReader> READER;
    public static @Nullable Function<String, BufferedWriter> WRITER;
    public static @Nullable BooleanSupplier SUPPORTS_READER;
    public static @Nullable BooleanSupplier SUPPORTS_WRITER;

    public static void reset() {
        READER = null;
        WRITER = null;
        SUPPORTS_READER = null;
        SUPPORTS_WRITER = null;
    }

    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        if (!supportsReader()) {
            return fail("May not invoke IOFactory#createReader(String) if IOFactory#supportsReader() returns false");
        }
        if (READER == null) {
            return fail("IOFactory#createReader(String) invoked illegally");
        }
        return READER.apply(resourceName);
    }

    @Override
    public BufferedWriter createWriter(String resourceName) throws IOException {
        if (!supportsReader()) {
            return fail("May not invoke IOFactory#createWriter(String) if IOFactory#supportsWriter() returns false");
        }
        if (WRITER == null) {
            return fail("IOFactory#createWriter(String) invoked illegally");
        }
        return WRITER.apply(resourceName);
    }

    @Override
    public boolean supportsReader() {
        if (SUPPORTS_READER != null) {
            return SUPPORTS_READER.getAsBoolean();
        }
        return false;
    }

    @Override
    public boolean supportsWriter() {
        if (SUPPORTS_WRITER != null) {
            return SUPPORTS_WRITER.getAsBoolean();
        }
        return false;
    }
}
