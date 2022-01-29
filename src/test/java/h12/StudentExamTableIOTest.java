package h12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class StudentExamTableIOTest {

    final IOFactory ioFactory = new FileSystemIOFactory();

    @Test
    void testWriteStudentExamTable() throws IOException {
        assumeTrue(ioFactory.supportsWriter());
        TableWithTitle table1 = TableGenerator.createTable(50, 2);
        TableWithTitle table2 = TableGenerator.createTable(100, 3);
        try (BufferedWriter wr = ioFactory.createWriter("test.txt")) {
            StudentExamEntry[] entries = {
                new StudentExamEntry("a", "b", 2, "1,3"),
            };
            StudentExamTableIO.writeStudentExamTable(wr, entries);
        }
    }

    @Test
    void testReadStudentExamTable() throws IOException {
        try (BufferedReader br = ioFactory.createReader("test.txt")) {
            TableWithTitle table = StudentExamTableIO.readStudentExamTable(br);
            System.out.println(table.getEntries()[0].getFirstName());
        }
    }

    @Test
    void testWriteAndReadStudentTable() throws IOException {
        assumeTrue(ioFactory.supportsWriter());
        assumeTrue(ioFactory.supportsReader());
        final int tableCount = 100;
        final int tableSize = 200;
        final String nameWithoutTitle = "test1.txt";
        final String nameWithTitle = "test2.txt";
        for (int i = 0; i < tableCount; i++) {
            final TableWithTitle toWrite = TableGenerator.createTable(tableSize, i);
            try (final BufferedWriter bw = ioFactory.createWriter(nameWithoutTitle)) {
                StudentExamTableIO.writeStudentExamTable(bw, toWrite.getEntries());
            }
            try (final BufferedWriter bw = ioFactory.createWriter(nameWithTitle)) {
                StudentExamTableIO.writeStudentExamTable(bw, toWrite.getEntries(), toWrite.getTitle());
            }
            final TableWithTitle toRead1;
            try (final BufferedReader br = ioFactory.createReader(nameWithoutTitle)) {
                toRead1 = StudentExamTableIO.readStudentExamTable(br);
            }
            assertNull(toRead1.getTitle());
            assertArrayEquals(toWrite.getEntries(), toRead1.getEntries());
            final TableWithTitle toRead2;
            try (final BufferedReader br = ioFactory.createReader(nameWithTitle)) {
                toRead2 = StudentExamTableIO.readStudentExamTable(br);
            }
            assertEquals(toWrite.getTitle(), toRead2.getTitle());
            assertArrayEquals(toWrite.getEntries(), toRead2.getEntries());
        }
    }
}
