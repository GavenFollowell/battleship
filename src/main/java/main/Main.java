package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.DefualtConverter;
import ui.TextPresenter;

public class Main {
    public static void main(String[] args) throws IOException {
        setDebugLogging();
        TextPresenter presenter = new TextPresenter();
        presenter.setConvert(new DefualtConverter());
        core.GameDriver g = new core.GameDriver(presenter);
        g.start();
    }

    private static void setDebugLogging() {
        Logger logger = Logger.getLogger("org.jline");
        logger.setLevel(Level.ALL);
    }
}
