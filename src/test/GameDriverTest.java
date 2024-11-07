package core;
import org.junit.jupiter.api.Test;

public class GameDriverTest {
    @Test
    void WhenGameStarts_WelcomePlayer() {
        PresenterDouble presenter = new PresenterDouble();
        GameDriver = new GameDriver(presenter);
        driver.start();
        assertEquals(1, presenter)
    }
}
