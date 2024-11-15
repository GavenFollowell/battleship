package core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class GridTest {

    Grid testGrid = new Grid(5, 5, Grid.defaultShipsFor5x5());

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 5);
        assertEquals(testGrid.numCols(), 5);
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
    public void aNewGridCorrectlyMarksShips() {
        assertTrue(testGrid.get(new Coord(1, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(2, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(3, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(5, 2)).hasShip());
        assertFalse(testGrid.get(new Coord(4, 2)).hasShip());
        assertFalse(testGrid.get(new Coord(1, 1)).hasShip());
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

    @Test
    public void allShipsHaveNotBeenShot() {
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(false, result);
    }

    @Test
    public void allShipsAreParticallyShot() {
        Cell cell1 = testGrid.get(new Coord(5, 1));
        cell1.setAsHit();
        Cell cell2 = testGrid.get(new Coord(1, 5));
        cell2.setAsHit();
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(false, result);
    }

    @Test
    public void allShipsAreShot() {
        List<Ship> shipList = testGrid.getShipList();
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                testGrid.get(coord).setAsShot();
                testGrid.get(coord).setAsHit();
            }
        }
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(true, result);
    }
}
