package h12.h2_4;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.Writer;

public class TutorBufferedWriter extends BufferedWriter {

    public static @Nullable Writer OUT;
    public static @Nullable TutorBufferedWriter INSTANCE;

    public static void reset0() {
        OUT = null;
        INSTANCE = null;
    }

    public TutorBufferedWriter(final Writer out) {
        super(out);
        OUT = out;
        INSTANCE = this;
    }

    public TutorBufferedWriter(final Writer out, final int sz) {
        super(out, sz);
        OUT = out;
        INSTANCE = this;
    }
}
