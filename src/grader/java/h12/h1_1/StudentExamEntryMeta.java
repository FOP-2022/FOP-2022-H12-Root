package h12.h1_1;

import h12.StudentExamEntry;
import org.jetbrains.annotations.Nullable;

/**
 * Statically set meta-data for the student {@link StudentExamEntry} implementation.
 *
 * <p>
 * Data cannot be set directly in the transformation phase, but must be set at runtime via static methods
 * added in during the transformation phase.
 * </p>
 */
public class StudentExamEntryMeta {

    public static String @Nullable [] ctor3Invokes;

    /**
     * The method invocations found in the constructor {@link StudentExamEntry#StudentExamEntry(String, String, int)}.
     */
    public static void setCtor3Invokes(String[] invokes) {
        ctor3Invokes = invokes;
    }
}
