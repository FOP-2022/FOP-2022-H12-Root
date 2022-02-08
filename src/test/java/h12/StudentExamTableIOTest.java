package h12;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class StudentExamTableIOTest {

    final IOFactory ioFactory = new FileSystemIOFactory();

    @Test
    void testWriteStudentExamTable() throws IOException {
        assumeTrue(ioFactory.supportsWriter());
        TableWithTitle table1 = TableGenerator.createTable(50, 2);
        TableWithTitle table2 = new TableWithTitle(null, TableGenerator.createEntries(50, 3));
        try (BufferedWriter wr = ioFactory.createWriter("test.txt")) {
            assertDoesNotThrow(() -> StudentExamTableIO.writeStudentExamTable(wr, table1.getEntries(), table1.getTitle()));
        }
        try (BufferedWriter wr = ioFactory.createWriter("test2.txt")) {
            assertDoesNotThrow(() -> StudentExamTableIO.writeStudentExamTable(wr, table2.getEntries()));
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
    void testReadStudentExamEntry() {
        for (int i = 0; i < 10; i++) {
            final String firstName = new String(TestConstants.A_Z, i, 5);
            final String lastName = new String(TestConstants.A_Z, i + 1, 5);
            final int enrollmentNumber = 54321 + 1234 * i;
            final String mark = TestConstants.VALID_MARKS[i * 7 % (TestConstants.VALID_MARKS.length - 1)];
            final String actualEntry = firstName + ":"
                + lastName + ":"
                + enrollmentNumber + ":"
                + (mark.equals("n/a") ? "" : mark);
            final StudentExamEntry entry = StudentExamTableIO.readStudentExamEntry(actualEntry);
            assertEquals(firstName, entry.getFirstName());
            assertEquals(lastName, entry.getLastName());
            assertEquals(enrollmentNumber, entry.getEnrollmentNumber());
            assertEquals(mark, entry.getMark());
        }
    }

    @Test
    void testWriteStudentExamTableComplex() throws IOException {
        assumeTrue(ioFactory.supportsWriter());
        assumeTrue(ioFactory.supportsReader());
        final int size = 50;
        final TableWithTitle table = TableGenerator.createTable(size, 2);
        final String fileName = "testWriteStudentExamTableComplex.txt";
        try (BufferedWriter bw = ioFactory.createWriter(fileName)) {
            StudentExamTableIO.writeStudentExamTable(bw, table.getEntries(), table.getTitle());
        }
        try (BufferedReader br = ioFactory.createReader(fileName)) {
            assertEquals("!" + table.getTitle(), br.readLine());
            assertEquals(size, assertDoesNotThrow(() -> Integer.parseInt(br.readLine())));
            @Nullable String next;
            for (int i = 0; i < size; i++) {
                if ((next = br.readLine()) == null) {
                    fail("Expected more lines");
                }
                final StudentExamEntry entry = table.getEntries()[i];
                final String expected = entry.getFirstName() + ":"
                    + entry.getLastName() + ":"
                    + entry.getEnrollmentNumber() + ":"
                    + (entry.getMark().equals("n/a") ? "" : entry.getMark());
                assertEquals(expected, next);
            }
            assertNull(br.readLine());
        }
    }

    @Test
    void testWriteAndReadStudentExamTable() throws IOException {
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
