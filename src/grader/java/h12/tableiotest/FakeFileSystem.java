package h12.tableiotest;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class FakeFileSystem {

    private final Map<String, String> resources = new HashMap<>();
    private final UnaryOperator<String> transformer;

    public FakeFileSystem(final UnaryOperator<String> transformer) {
        this.transformer = transformer;
    }

    public FakeFileSystem() {
        this(UnaryOperator.identity());
    }

    private void putResource(final String resourceName, final String resource) {
        resources.put(resourceName, transformer.apply(resource));
    }

    public BufferedReader createReader(final String resourceName) {
        return new BufferedReader(new DeferredReader(resourceName));
    }

    public BufferedWriter createWriter(final String resourceName) {
        return new BW(new StringWriter(), resourceName);
    }

    private String getResource(final String resourceName) throws IOException {
        final @Nullable String resource = resources.get(resourceName);
        if (resource == null) {
            throw new IOException("Fake-resource %s does not exist. The writer was probably not closed in time.".formatted(resourceName));
        }
        return resource;
    }

    private class DeferredReader extends Reader {

        private final String resourceName;

        /**
         * Set as late as possible. // TODO: This is too nice.
         */
        private @Nullable Reader delegate;

        private DeferredReader(final String resourceName) {
            this.resourceName = resourceName;
        }

        private Reader setDelegate() throws IOException {
            return new StringReader(getResource(resourceName));
        }

        @Override
        public int read(final char[] cbuf, final int off, final int len) throws IOException {
            if (delegate == null) {
                delegate = setDelegate();
            }
            return delegate.read(cbuf, off, len);
        }

        @Override
        public void close() throws IOException {
            if (delegate == null) {
                delegate = setDelegate();
            }
            delegate.close();
        }
    }

    private class BW extends BufferedWriter {

        private final StringWriter out;
        private final String resourceName;

        public BW(final StringWriter out, final String resourceName) {
            super(out);
            this.out = out;
            this.resourceName = resourceName;
        }

        @Override
        public void flush() throws IOException {
            super.flush();
            putResource(resourceName, out.toString());
        }

        @Override
        public void close() throws IOException {
            super.close();
            putResource(resourceName, out.toString());
        }
    }
}
