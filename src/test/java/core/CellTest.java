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
    void returnsIsHitCorrectlyWithShipAndShot() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        assertTrue(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void returnsIsMissCorrectlyWithNoShipAndShot() {
        Cell cell = new Cell(false);
        cell.setAsShot();
        assertFalse(cell.cellIsHit());
        assertTrue(cell.cellIsMiss());
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
    }

    @Test
    void cellWithShipThatHasBeenShotReturnsTrueForIsHit() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        assertFalse(cell.isEmpty());
        assertTrue(cell.hasBeenShot());
        assertTrue(cell.cellIsHit());
        assertFalse(cell.cellIsMiss());
    }

    @Test
    void cellWithoutShipThatHasNotBeenShotReturnsTrueForIsMiss() {
        Cell cell = new Cell();
        cell.setAsShot();
        assertTrue(cell.isEmpty());
        assertTrue(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
        assertTrue(cell.cellIsMiss());
    }

    @Test
    void cellWithShipReturnsFalseForIsEmpty() {
        Cell cell = new Cell(true);
        assertFalse(cell.isEmpty());
    }

    @Test
    void settingAsShotMultipleTimesDoesNotChangeState() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.setAsShot();
        assertTrue(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
    }

    @Test
    void resetSetsAllVariablesToFalse() {
        Cell cell = new Cell(true);
        cell.setAsShot();
        cell.reset();
        assertFalse(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
    }

    @Test
    void setAsHitWorks() {
        Cell cell = new Cell(false);
        cell.setAsHit();
        assertTrue(cell.cellIsHit());
    }
}
