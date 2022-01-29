package h12;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class StudentExamTableIOTest {

    final IOFactory ioFactory = new ResourceIOFactory();



    @Test
    void testWriteStudentExamTable() throws IOException {
        try (BufferedWriter wr = ioFactory.createWriter("test.txt")) {
            StudentExamEntry[] entries = {
                new StudentExamEntry("a", "b", 2, "1,3")
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
}
