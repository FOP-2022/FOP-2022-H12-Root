package h12.tablegenerator;

import h12.StudentExamEntry;
import h12.TableGenerator;
import h12.SolutionTableGenerator;

import java.util.Random;

@SuppressWarnings("unused")
public class TutorTableGenerator {

    public static boolean USE_SOLUTION;

    public static StudentExamEntry[] createEntries(int size, long seed) {
        if (USE_SOLUTION) {
            return SolutionTableGenerator.createEntries(size, seed);
        } else {
            return TableGenerator.createEntries(size, seed);
        }
    }

    public static StudentExamEntry[] createEntries(int size, Random random) {
        if (USE_SOLUTION) {
            return SolutionTableGenerator.createEntries(size, random);
        } else {
            // if this method was inserted via bytecode, it is safe to assume that
            // the student's TableGenerator implementation also has a method of the same signature
            return TableGenerator.createEntries(size, random);
        }
    }
}
