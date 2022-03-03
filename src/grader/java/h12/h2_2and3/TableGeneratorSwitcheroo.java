package h12.h2_2and3;

import h12.StudentExamEntry;
import h12.TableGenerator;
import h12.TutorTableGenerator;

import java.util.Random;

public class TableGeneratorSwitcheroo {

    public static boolean USE_TUTOR;

    public static StudentExamEntry[] createEntries(int size, long seed) {
        if (USE_TUTOR) {
            return TutorTableGenerator.createEntries(size, seed);
        } else {
            return TableGenerator.createEntries(size, seed);
        }
    }

    public static StudentExamEntry[] createEntries(int size, Random random) {
        if (USE_TUTOR) {
            return TutorTableGenerator.createEntries(size, random);
        } else {
            // if this method was inserted via bytecode, it is safe to assume that
            // the student's TableGenerator implementation also has a method of the same signature
            return TableGenerator.createEntries(size, random);
        }
    }
}
