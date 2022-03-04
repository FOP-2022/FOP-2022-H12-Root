package h12.h2_4;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

public class TutorFileReader extends Reader {

    @FunctionalInterface
    interface FunctionalRead {
        int read(char[] cbuf, int off, int len) throws IOException;
    }

    public static @Nullable FunctionalRead FUNCTIONAL_READ;

    public static @Nullable String FILE_NAME;
    public static @Nullable File FILE;
    public static @Nullable FileDescriptor FD;
    public static @Nullable TutorFileReader INSTANCE;
    public static boolean CLOSED;

    public static void reset0() {
        FILE_NAME = null;
        FILE = null;
        FD = null;
        INSTANCE = null;
        CLOSED = false;
    }

    public TutorFileReader(final String fileName) {
        FILE_NAME = fileName;
        INSTANCE = this;
    }

    public TutorFileReader(final File file) {
        FILE = file;
        INSTANCE = this;
    }

    public TutorFileReader(final FileDescriptor fd) {
        FD = fd;
        INSTANCE = this;
    }

    public TutorFileReader(final String fileName, final Charset charset) {
        FILE_NAME = fileName;
        INSTANCE = this;
    }

    public TutorFileReader(final File file, final Charset charset) {
        FILE = file;
        INSTANCE = this;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (FUNCTIONAL_READ != null) {
            return FUNCTIONAL_READ.read(cbuf, off, len);
        }
        return -1;
    }

    @Override
    public void close() {
        CLOSED = true;
    }
}
