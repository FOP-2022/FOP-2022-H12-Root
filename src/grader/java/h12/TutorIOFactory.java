package h12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TutorIOFactory implements IOFactory {

    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        return null;
    }

    @Override
    public BufferedWriter createWriter(String resourceName) throws IOException {
        return null;
    }

    @Override
    public boolean supportsReader() {
        return false;
    }

    @Override
    public boolean supportsWriter() {
        return false;
    }
}
