package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PresenterDouble implements Presenter {
    List<String> displayedMessages = new ArrayList<>();

    public void displayMessage(String s) {
        displayedMessages.add(s);
    }

    public void displayGrid(Grid g) {
        // TODO
    }

    public void displayOptions(String prompt, Map<String, Runnable> choices) {}
}
