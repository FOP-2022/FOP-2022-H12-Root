package h12.h2_1;

import h12.StudentExamEntry;

import java.io.IOException;
import java.io.Writer;

@SuppressWarnings("DuplicatedCode")
public class H2_1_Utils {

    public static void writeStudentExamTableTutor(Writer writer, StudentExamEntry[] entries, String title) throws IOException {
        writer.write('!');
        writer.write(title);
        writer.write('\n');
        writeStudentExamTableTutor(writer, entries);
    }

    public static void writeStudentExamTableTutor(Writer writer, StudentExamEntry[] entries) throws IOException {
        writer.write(Integer.toString(entries.length));
        writer.write('\n');
        for (StudentExamEntry entry : entries) {
            writeStudentExamEntryTutor(writer, entry);
            writer.write('\n');
        }
    }

    public static void writeStudentExamEntryTutor(Writer writer, StudentExamEntry entry) throws IOException {
        writer.write(entry.getFirstName());
        writer.write(':');
        writer.write(entry.getLastName());
        writer.write(':');
        writer.write(Integer.toString(entry.getEnrollmentNumber()));
        writer.write(':');
        final String mark = entry.getMark();
        if (!mark.equals("n/a")) {
            writer.write(mark);
        }
    }
}
