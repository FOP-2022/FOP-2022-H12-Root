package h12;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceIOFactory implements IOFactory {

    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        final @Nullable InputStream resourceStream = getClass().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            throw new IOException("Could not find %s/%s".formatted(getClass().getPackageName(), resourceName));
        }
        return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
    }

    @Override
    public BufferedWriter createWriter(String resourceName) {
        throw new UnsupportedOperationException("%s does not support writing!".formatted(getClass().getName()));
    }

    @Override
    public boolean supportsReader() {
        return true;
    }

    @Override
    public boolean supportsWriter() {
        return false;
    }
}
