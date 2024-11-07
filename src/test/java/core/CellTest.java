package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
    @Test
    void defaultCellHoldsProperlyMade() {
        Cell cell = new Cell();
        assertTrue(cell.isEmpty());
        assertFalse(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void cellCanBeMadeWithATrueSHipValue() {
        Cell cell = new Cell(true);
        assertFalse(cell.isEmpty());
        assertFalse(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void canAddShipToEmptyCell() {
        Cell cell = new Cell();
        cell.setAsShip();
        assertFalse(cell.isEmpty());
        assertFalse(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void canMarkShipAsShot() {
        Cell cell = new Cell();
        cell.setAsShot();
        assertTrue(cell.isEmpty());
        assertTrue(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void canMarkShipAsHit() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        cell.setAsHit();
        assertFalse(cell.isEmpty());
        assertTrue(cell.hasBeenShot());
        assertTrue(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void canMarkShipAsMiss() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.setAsMiss();
        assertTrue(cell.isEmpty());
        assertTrue(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertTrue(cell.cellIsMiss());
    }

    @Test
    void cantMarkAnEmptyCellAsHit() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.hit();
        assertFalse(cell.cellIsHit());
    }

    @Test
    void cantMarkAShipCellSpaceAsMiss() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        cell.miss();
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void cellWithShipReturnsFalseForIsEmpty() {
        Cell cell = new Cell(true);
        assertFalse(cell.isEmpty());
    }

    /*
    @Test
    void hittingWithoutBeingShotDoesNotMarkAsHit() {
        Cell cell = new Cell(true);
        cell.hit();
        assertFalse(cell.cellIsHit());
    }

    @Test
    void missingWithoutBeingShotDoesNotMarkAsMiss() {
        Cell cell = new Cell();
        cell.miss();
        assertFalse(cell.cellIsMiss());
    }
    */

    @Test
    void settingAsShotMultipleTimesDoesNotChangeState() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.setAsShot();
        assertTrue(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void settingAsHitThenMissDoesNotChangeStateToMiss() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        cell.setAsHit();
        cell.miss();
        assertTrue(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void settingAsMissThenHitDoesNotChangeStateToHit() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.setAsMiss();
        cell.hit();
        assertFalse(cell.cellIsHit());
        assertTrue(cell.cellIsMiss());
    }
}
