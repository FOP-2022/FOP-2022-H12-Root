package h12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemIOFactory implements IOFactory {

    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        return new BufferedReader(new FileReader(resourceName));
    }

    @Override
    public BufferedWriter createWriter(String resourceName) throws IOException {
        return new BufferedWriter(new FileWriter(resourceName));
    }

    @Override
    public boolean supportsReader() {
        return true;
    }

    @Override
    public boolean supportsWriter() {
        return true;
    }
}
