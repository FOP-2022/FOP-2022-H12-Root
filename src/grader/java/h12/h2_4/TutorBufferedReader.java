package h12.h2_4;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.Reader;

public class TutorBufferedReader extends BufferedReader {

    public static @Nullable Reader IN;
    public static @Nullable TutorBufferedReader INSTANCE;

    public static void reset0() {
        IN = null;
        INSTANCE = null;
    }

    public TutorBufferedReader(final Reader in, final int sz) {
        super(in, sz);
        IN = in;
        INSTANCE = this;
    }

    public TutorBufferedReader(final Reader in) {
        super(in);
        IN = in;
        INSTANCE = this;
    }
}
