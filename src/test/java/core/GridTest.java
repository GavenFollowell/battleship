package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;
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
                assertNotEquals(null, testGrid.getCell(new Coord(i, j)));
            }
        }
    }

    @Test
    public void aNewGridCorrectlyMarksShips() {
        assertTrue(testGrid.getCell(new Coord(1, 2)).hasShip());
        assertTrue(testGrid.getCell(new Coord(2, 2)).hasShip());
        assertTrue(testGrid.getCell(new Coord(3, 2)).hasShip());
        assertTrue(testGrid.getCell(new Coord(5, 2)).hasShip());
        assertFalse(testGrid.getCell(new Coord(4, 2)).hasShip());
        assertFalse(testGrid.getCell(new Coord(1, 1)).hasShip());
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
    public void addShipUpdatesShipListCorrectly() {
        Ship ship1 = new Ship(new Coord(1, 2), 3, VERTICAL, "Submarine");
        Ship ship2 = new Ship(new Coord(5, 1), 5, HORIZONTAL, "Carrier");
        Ship ship3 = new Ship(new Coord(1, 5), 3, VERTICAL, "Destroyer");
        Ship addedShip = new Ship(new Coord(4, 5), 1, VERTICAL, "Ship");
        testGrid.addShip(addedShip);
        assertTrue(assertEqualShips(ship1, testGrid.getShipList().get(0)));
        assertTrue(assertEqualShips(ship2, testGrid.getShipList().get(1)));
        assertTrue(assertEqualShips(ship3, testGrid.getShipList().get(2)));
        assertTrue(assertEqualShips(addedShip, testGrid.getShipList().get(3)));
    }

    public static boolean assertEqualShips(Ship expected, Ship actual) {
        if (expected.getSize() != actual.getSize()) {
            return false;
        }
        for (int i = 0; i < expected.getSize(); i++) {
            if (!expected.getCoordList().get(i).isEqual(actual.getCoordList().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void whenShootingAndNotAlreadyShotChangeCellToShot() {
        Cell shootCell = testGrid.getCell(new Coord(2, 2));
        testGrid.shoot(new Coord(2, 2));
        assertTrue(shootCell.hasBeenShot());
    }

    @Test
    public void allShipsHaveNotBeenShot() {
        boolean result = testGrid.allShipsAreSunk();
        assertFalse(result);
    }

    @Test
    public void allShipsAreParticallyShot() {
        Cell cell1 = testGrid.getCell(new Coord(5, 1));
        cell1.setAsHit();
        Cell cell2 = testGrid.getCell(new Coord(1, 5));
        cell2.setAsHit();
        boolean result = testGrid.allShipsAreSunk();
        assertFalse(result);
    }

    @Test
    public void allShipsAreShot() {
        List<Ship> shipList = testGrid.getShipList();
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                testGrid.getCell(coord).setAsShot();
                testGrid.getCell(coord).setAsHit();
            }
        }
        boolean result = testGrid.allShipsAreSunk();
        assertTrue(result);
    }

    @Test
    public void isShipOnGridReturnsTrueWhenShipIsShipOnGrid() {
        List<Ship> ships = testGrid.getShipList();
        assertTrue(testGrid.isShipOnGrid(ships.get(0)));
    }

    @Test
    public void isShipOnGridReturnsFalseWhenShipIsShipOnGrid() {
        Coord c1 = new Coord(1, 2);
        Ship otherShip = new Ship(c1, 6, VERTICAL, "BattleShip");
        assertFalse(testGrid.isShipOnGrid(otherShip));
    }

    @Test
    public void isShipSunkReturnsTrueIfAllCellsMarkedAsHit() {
        Ship ship = new Ship(new Coord(1, 2), 3, VERTICAL, "BattleShip");
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(2, 2);
        Coord c3 = new Coord(3, 2);
        testGrid.getCell(c1).setAsShip();
        testGrid.getCell(c2).setAsShip();
        testGrid.getCell(c3).setAsShip();
        testGrid.getCell(c1).setAsShot();
        testGrid.getCell(c2).setAsShot();
        assertFalse(testGrid.isShipSunk(ship));
        testGrid.getCell(c3).setAsShot();
        assertTrue(testGrid.isShipSunk(ship));
    }
}
