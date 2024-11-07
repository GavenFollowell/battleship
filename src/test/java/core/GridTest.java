package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridTest {
    Grid testGrid = new Grid(3, 2);

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 3);
        assertEquals(testGrid.numCols(), 2);
    }

    @Test
    public void aNewGridHasNoneNullCells() {
        for (int i = 1; i <= testGrid.numRows(); i++) {
            for (int j = 1; j <= testGrid.numCols(); j++) {
                assertNotEquals(null, testGrid.get(new Coord(i, j)));
            }
        }
    }

    @Test
    public void isTheCoordinateWithinGrid() {
        Boolean result = testGrid.isValid(new Coord(3, 2));
        assertEquals(true, result);
    }

    @Test
    public void isTheCoordinateNotWithinGrid() {
        Boolean result = testGrid.isValid(new Coord(5, 6));
        assertEquals(false, result);
    }
}
