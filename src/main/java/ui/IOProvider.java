package ui;

import java.io.InputStream;
import java.io.PrintStream;

public interface IOProvider {
    PrintStream out();

    InputStream in();
}
