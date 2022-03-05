package h12.studentexamtableio;

import h12.OverridingTutorStudentExamEntry;
import h12.StudentExamEntry;
import h12.TableWithTitle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@SuppressWarnings("DuplicatedCode")
public class SolutionStudentExamTableIO {

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries, String title) throws IOException {
        writer.write('!');
        writer.write(title);
        writer.write('\n');
        writeStudentExamTable(writer, entries);
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries) throws IOException {
        writer.write(Integer.toString(entries.length));
        writer.write('\n');
        for (StudentExamEntry entry : entries) {
            writeStudentExamEntry(writer, entry);
            writer.write('\n');
        }
    }

    public static void writeStudentExamEntry(Writer writer, StudentExamEntry entry) throws IOException {
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

    public static TableWithTitle readStudentExamTable(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        final String tableName;
        if (line.startsWith("!")) {
            tableName = line.substring(1);
            line = reader.readLine();
        } else {
            tableName = null;
        }
        final StudentExamEntry[] entries = new StudentExamEntry[Integer.parseInt(line)];
        int i = 0;
        while ((line = reader.readLine()) != null) {
            entries[i++] = readStudentExamEntry(line);
        }
        return new TableWithTitle(tableName, entries);
    }

    public static StudentExamEntry readStudentExamEntry(String line) {
        final String[] elements = line.split(":");
        final String firstName = elements[0];
        final String lastName = elements[1];
        final int enrollmentNumber = Integer.parseInt(elements[2]);
        final String mark;
        if (elements.length < 4) {
            mark = "n/a";
        } else {
            mark = elements[3];
        }
        return new OverridingTutorStudentExamEntry(firstName, lastName, enrollmentNumber, mark);
    }
}
