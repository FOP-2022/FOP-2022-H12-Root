package h12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public class StudentExamTableIO {

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries, String input) throws IOException {
        writer.write('!');
        writer.write(input);
        writer.write('\n');
        writeStudentExamTable(writer, entries);
    }

    public static void writeStudentExamTable(Writer writer, StudentExamEntry[] entries) throws IOException {
        writer.write(Integer.toString(entries.length));
        writer.write('\n');
        for (StudentExamEntry entry : entries) {
            writeStudentEntry(writer, entry);
        }
    }

    private static void writeStudentEntry(Writer writer, StudentExamEntry entry) throws IOException {
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
        writer.write('\n');
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

    private static StudentExamEntry readStudentExamEntry(String line) throws IOException {
        final String[] elements = line.split(":");
        final String firstName = elements[0];
        final String lastName = elements[1];
        final int enrollmentNumber = Integer.parseInt(elements[2]);
        final String mark;
        if (elements[3].isEmpty()) {
            mark = "n/a";
        } else {
            mark = elements[3];
        }
        return new StudentExamEntry(firstName, lastName, enrollmentNumber, mark);
    }

    void testWriteAndReadStudentExamTable() {
    }
}