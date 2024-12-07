package ui;

import static org.junit.jupiter.api.Assertions.*;

import core.Cell;
import core.Coord;
import core.Grid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TextPresenterTest {

    @Test
    void whenUserChoosesOption_thenTheCorrectFunctionIsCalled() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        ByteArrayInputStream in = new ByteArrayInputStream("Start\n".getBytes());

        Grid grid = new Grid(3, 3);
        var ref =
                new Object() {
                    boolean startCalled = false;
                    boolean stopCalled = false;
                };
        TextPresenter presenter = new TextPresenter(out, in);
        presenter.displayOptions(
                "prompt",
                Map.of(
                        "Start",
                                () -> {
                                    ref.startCalled = true;
                                },
                        "Stop",
                                () -> {
                                    ref.stopCalled = true;
                                }));
        assertTrue(ref.startCalled);
        assertFalse(ref.stopCalled);
    }

    @Test
    void whenDisplayGridIsCalled_TheGridIsSentToTheOutputStream() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(3, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);
        String expected =
                """
                             1   2   3\s

                         A   0   0   0\s

                         B   0   0   0\s

                         C   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenUserChoosesStopOption_thenStopFunctionIsCalled() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        ByteArrayInputStream input =
                new ByteArrayInputStream("Stop\n".getBytes(StandardCharsets.UTF_8));

        var ref =
                new Object() {
                    boolean startCalled = false;
                    boolean stopCalled = false;
                };

        TextPresenter presenter = new TextPresenter(out, input);
        presenter.displayOptions(
                "prompt",
                Map.of(
                        "Start",
                                () -> {
                                    ref.startCalled = true;
                                },
                        "Stop",
                                () -> {
                                    ref.stopCalled = true;
                                }));

        assertFalse(ref.startCalled);
        assertTrue(ref.stopCalled);
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGrid() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(2, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);

        String expected =
                """
                             1   2   3\s

                         A   0   0   0\s

                         B   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenAskForCoordinateItReturnsIT() {
        Grid grid = new Grid(2, 3);
        TestIOProvider ioProvider = TestIOProvider.withInput("B3\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord expected = new Coord(2, 3);
        Coord actual = presenter.askForCoordinate(grid);
        boolean Result = expected.isEqual(actual);
        assertTrue(Result);
        assertEquals(expected.row, actual.row);
        assertEquals(expected.col, actual.col);
    }

    @Test
    void whenAskForCoordinateIsFalse() {
        Grid grid = new Grid(2, 3);
        TestIOProvider ioProvider = TestIOProvider.withInput("C5\nB3\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord expected = new Coord(2, 3);
        Coord actual = presenter.askForCoordinate(grid);
        boolean Result = expected.isEqual(actual);
        String expected_m = "Not within the Grid!";
        assertEquals(expected_m, ioProvider.getOutput());
        assertTrue(Result);
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGridThatHas1hit() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(2, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        Cell cell = grid.getCell(new Coord(1, 1));
        cell.setAsHit();
        presenter.displayGrid(grid);
        String expected =
                """
                             1   2   3\s

                         A   X   0   0\s

                         B   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGridThatHas2hitsAMissAndAShip() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid g = new Grid(5, 7);
        TextPresenter presenter = new TextPresenter(ioProvider);

        g.getCell(new Coord(1, 1)).setAsHit();
        g.getCell(new Coord(4, 5)).setAsHit();
        g.getCell(new Coord(5, 7)).setAsHit();
        g.getCell(new Coord(2, 6)).setAsMiss();
        g.getCell(new Coord(3, 2)).setAsShip();

        presenter.displayGrid(g);
        String expected =
                """
                             1   2   3   4   5   6   7\s

                         A   X   0   0   0   0   0   0\s

                         B   0   0   0   0   0   *   0\s

                         C   0   ~   0   0   0   0   0\s

                         D   0   0   0   0   X   0   0\s

                         E   0   0   0   0   0   0   X\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }
}
