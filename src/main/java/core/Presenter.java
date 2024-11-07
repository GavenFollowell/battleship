package core;

import java.util.Map;

public interface Presenter {
    void displayMessage(String s);

    void displayGrid(Grid g);

    Coord askForCoordinate(Grid g);

    void displayOptions(String prompt, Map<String, Runnable> choices);
}
