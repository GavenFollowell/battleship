package ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

final class TestIOProvider implements IOProvider {
    private final ByteArrayOutputStream baos;
    private final PrintStream out;
    private final InputStream in;

    TestIOProvider(String input) {
        this.baos = new ByteArrayOutputStream();
        this.out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        this.in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }

    public static TestIOProvider withInput(String input) {
        return new TestIOProvider(input);
    }

    public PrintStream out() {
        return out;
    }

    public InputStream in() {
        return in;
    }

    String getOutput() {
        return baos().toString(StandardCharsets.UTF_8);
    }

    private ByteArrayOutputStream baos() {
        return baos;
    }
}
