package h12.tablegenerator;

import h12.StudentExamEntry;
import h12.TableGenerator;
import h12.TableWithTitle;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class TutorTableGenerator {

    public static boolean USE_SOLUTION;
    public static @Nullable IntConsumer SIZE;
    public static @Nullable LongConsumer SEED;
    public static @Nullable Consumer<Random> RANDOM;
    public static @Nullable UnaryOperator<StudentExamEntry[]> CREATE_ENTRIES;
    public static @Nullable UnaryOperator<TableWithTitle> CREATE_TABLE;

    public static void reset() {
        USE_SOLUTION = false;
        SIZE = null;
        SEED = null;
        RANDOM = null;
        CREATE_ENTRIES = null;
        CREATE_TABLE = null;
    }

    private static void acceptSize(int size) {
        if (SIZE != null) {
            SIZE.accept(size);
        }
    }

    private static void acceptSeed(long seed) {
        if (SEED != null) {
            SEED.accept(seed);
        }
    }

    private static void acceptRandom(Random random) {
        if (RANDOM != null) {
            RANDOM.accept(random);
        }
    }

    private static StudentExamEntry[] acceptEntries(StudentExamEntry[] entries) {
        if (CREATE_ENTRIES != null) {
            return CREATE_ENTRIES.apply(entries);
        }
        return entries;
    }

    private static TableWithTitle acceptTable(TableWithTitle table) {
        if (CREATE_TABLE != null) {
            return CREATE_TABLE.apply(table);
        }
        return table;
    }

    public static StudentExamEntry[] createEntriesDirect(int size, long seed) {
        return USE_SOLUTION
            ? SolutionTableGenerator.createEntries(size, seed)
            : TableGenerator.createEntries(size, seed);
    }

    public static StudentExamEntry[] createEntries(int size, long seed) {
        acceptSize(size);
        acceptSeed(seed);
        return acceptEntries(createEntriesDirect(size, seed));
    }

    public static StudentExamEntry[] createEntriesDirect(int size, Random random) {
        return USE_SOLUTION
            ? SolutionTableGenerator.createEntries(size, random)
            // if this method was inserted via bytecode, it is safe to assume that
            // the student's TableGenerator implementation also has a method of the same signature
            : TableGenerator.createEntries(size, random);
    }

    public static StudentExamEntry[] createEntries(int size, Random random) {
        acceptSize(size);
        acceptRandom(random);
        return acceptEntries(createEntriesDirect(size, random));
    }

    public static TableWithTitle createTableDirect(int size, long seed) {
        return USE_SOLUTION
            ? SolutionTableGenerator.createTable(size, seed)
            : TableGenerator.createTable(size, seed);
    }

    public static TableWithTitle createTable(int size, long seed) {
        acceptSize(size);
        acceptSeed(seed);
        return acceptTable(createTableDirect(size, seed));
    }

    public static TableWithTitle createTableDirect(int size, Random random) {
        return USE_SOLUTION
            ? SolutionTableGenerator.createTable(size, random)
            : TableGenerator.createTable(size, random);
    }

    public static TableWithTitle createTable(int size, Random random) {
        acceptSize(size);
        acceptRandom(random);
        // if this method was inserted via bytecode, it is safe to assume that
        // the student's TableGenerator implementation also has a method of the same signature
        return acceptTable(createTableDirect(size, random));
    }
}
