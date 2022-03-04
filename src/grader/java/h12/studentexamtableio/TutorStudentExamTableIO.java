package h12.studentexamtableio;

import h12.StudentExamEntry;
import h12.StudentExamTableIO;
import h12.TableWithTitle;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class TutorStudentExamTableIO {

    public interface Write {
        void writeStudentExamTable(Writer writer, StudentExamEntry[] entries, String title) throws IOException;

        void writeStudentExamTable(Writer writer, StudentExamEntry[] entries) throws IOException;

        void writeStudentExamEntry(Writer writer, StudentExamEntry entry) throws IOException;
    }

    public static class StoreWrite implements Write {

        public interface WithWriter {
            Writer writer();
        }

        public record WriteData0(Writer writer, StudentExamEntry[] entries, String title) implements WithWriter {}

        public record WriteData1(Writer writer, StudentExamEntry[] entries) implements WithWriter {}

        public record WriteData2(Writer writer, StudentExamEntry entry) implements WithWriter {}

        public final List<WriteData0> writeData0List = new ArrayList<>();
        public final List<WriteData1> writeData1List = new ArrayList<>();
        public final List<WriteData2> writeData2List = new ArrayList<>();

        public boolean checkForAll(Predicate<? super WithWriter> predicate) {
            return writeData0List.stream().allMatch(predicate)
                && writeData1List.stream().allMatch(predicate)
                && writeData2List.stream().allMatch(predicate);
        }

        @Override
        public void writeStudentExamTable(final Writer writer, final StudentExamEntry[] entries, final String title) throws IOException {
            writeData0List.add(new WriteData0(writer, entries, title));
        }

        @Override
        public void writeStudentExamTable(final Writer writer, final StudentExamEntry[] entries) throws IOException {
            writeData1List.add(new WriteData1(writer, entries));
        }

        @Override
        public void writeStudentExamEntry(final Writer writer, final StudentExamEntry entry) throws IOException {
            writeData2List.add(new WriteData2(writer, entry));
        }
    }

    public interface Read {
        TableWithTitle readStudentExamTable(BufferedReader reader) throws IOException;

        StudentExamEntry readStudentExamEntry(String line);
    }

    public enum ExecutionType {
        USE_ORIGINAL,
        USE_SOLUTION_INFORM_CUSTOM,
        USE_SOLUTION_NO_INFORM,
        USE_CUSTOM,
    }

    public static ExecutionType EXECUTION_TYPE = ExecutionType.USE_ORIGINAL;
    public static @Nullable Write WRITE;
    public static @Nullable Read READ;

    public static void reset() {
        EXECUTION_TYPE = ExecutionType.USE_ORIGINAL;
        WRITE = null;
        READ = null;
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries, String title) throws IOException {
        switch (EXECUTION_TYPE) {
            case USE_ORIGINAL -> StudentExamTableIO.writeStudentExamTable(writer, entries, title);
            case USE_SOLUTION_INFORM_CUSTOM -> {
                Objects.requireNonNull(WRITE).writeStudentExamTable(writer, entries, title);
                SolutionStudentExamTableIO.writeStudentExamTable(writer, entries, title);
            }
            case USE_SOLUTION_NO_INFORM -> SolutionStudentExamTableIO.writeStudentExamTable(writer, entries, title);
            case USE_CUSTOM -> {
                Objects.requireNonNull(WRITE).writeStudentExamTable(writer, entries, title);
            }
        }
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries) throws IOException {
        switch (EXECUTION_TYPE) {
            case USE_ORIGINAL -> StudentExamTableIO.writeStudentExamTable(writer, entries);
            case USE_SOLUTION_INFORM_CUSTOM -> {
                Objects.requireNonNull(WRITE).writeStudentExamTable(writer, entries);
                SolutionStudentExamTableIO.writeStudentExamTable(writer, entries);
            }
            case USE_SOLUTION_NO_INFORM -> SolutionStudentExamTableIO.writeStudentExamTable(writer, entries);
            case USE_CUSTOM -> {
                Objects.requireNonNull(WRITE).writeStudentExamTable(writer, entries);
            }
        }
    }

    public static void writeStudentExamEntry(Writer writer, StudentExamEntry entry) throws IOException {
        switch (EXECUTION_TYPE) {
            case USE_ORIGINAL -> StudentExamTableIO.writeStudentExamEntry(writer, entry);
            case USE_SOLUTION_INFORM_CUSTOM -> {
                Objects.requireNonNull(WRITE).writeStudentExamEntry(writer, entry);
                SolutionStudentExamTableIO.writeStudentExamEntry(writer, entry);
            }
            case USE_SOLUTION_NO_INFORM -> SolutionStudentExamTableIO.writeStudentExamEntry(writer, entry);
            case USE_CUSTOM -> Objects.requireNonNull(WRITE).writeStudentExamEntry(writer, entry);
        }
    }

    public static TableWithTitle readStudentExamTable(BufferedReader reader) throws IOException {
        return switch (EXECUTION_TYPE) {
            case USE_ORIGINAL -> StudentExamTableIO.readStudentExamTable(reader);
            case USE_SOLUTION_INFORM_CUSTOM -> {
                Objects.requireNonNull(READ).readStudentExamTable(reader);
                yield SolutionStudentExamTableIO.readStudentExamTable(reader);
            }
            case USE_SOLUTION_NO_INFORM -> SolutionStudentExamTableIO.readStudentExamTable(reader);
            case USE_CUSTOM -> Objects.requireNonNull(READ).readStudentExamTable(reader);
        };
    }

    public static StudentExamEntry readStudentExamEntry(String line) {
        return switch (EXECUTION_TYPE) {
            case USE_ORIGINAL -> StudentExamTableIO.readStudentExamEntry(line);
            case USE_SOLUTION_INFORM_CUSTOM -> {
                Objects.requireNonNull(READ).readStudentExamEntry(line);
                yield SolutionStudentExamTableIO.readStudentExamEntry(line);
            }
            case USE_SOLUTION_NO_INFORM -> SolutionStudentExamTableIO.readStudentExamEntry(line);
            case USE_CUSTOM -> Objects.requireNonNull(READ).readStudentExamEntry(line);
        };
    }
}
