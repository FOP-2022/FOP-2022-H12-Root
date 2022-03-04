package h12.h2_4;

import h12.FileSystemIOFactory;
import h12.TutorTableGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h12")
public class FileSystemIOFactoryGeneralTutorTest {

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFileSystemIOFactoryReader() throws IOException {
        final FileSystemIOFactory fileSystemIOFactory = new FileSystemIOFactory();
        for (int i = 0; i < 10; i++) {
            TutorBufferedReader.reset0();
            TutorFileReader.reset0();
            final Random random = new Random(98);
            final String resourceName = TutorTableGenerator.createRandomString(random);
            final String expected = TutorTableGenerator.createRandomString(random);
            TutorFileReader.FUNCTIONAL_READ = new StringReader(expected + "\n")::read;
            try (BufferedReader br = fileSystemIOFactory.createReader(resourceName)) {
                assertEquals(expected, br.readLine());
            }
            assertEquals(resourceName, TutorFileReader.FILE_NAME);
            assertEquals(TutorBufferedReader.IN, TutorFileReader.INSTANCE);
        }
    }

    @Test
    @ExtendWith(JagrExecutionCondition.class)
    public void testFileSystemIOFactoryWriter() throws IOException {
        final FileSystemIOFactory fileSystemIOFactory = new FileSystemIOFactory();
        for (int i = 0; i < 10; i++) {
            TutorBufferedWriter.reset0();
            TutorFileWriter.reset0();
            final Random random = new Random(93);
            final String resourceName = TutorTableGenerator.createRandomString(random);
            final String expected = TutorTableGenerator.createRandomString(random);
            final StringWriter stringWriter = new StringWriter();
            TutorFileWriter.FUNCTIONAL_WRITE = stringWriter::write;
            try (BufferedWriter bw = fileSystemIOFactory.createWriter(resourceName)) {
                bw.write(expected);
            }
            assertEquals(expected, stringWriter.toString());
            assertEquals(resourceName, TutorFileWriter.FILE_NAME);
            assertEquals(TutorBufferedWriter.OUT, TutorFileWriter.INSTANCE);
        }
    }
}
