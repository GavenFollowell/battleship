package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordTest {

    @Test
    public void asIntReturnsCorrectInteger() {
        assertEquals(1, Coord.asInt("A"));
        assertEquals(26, Coord.asInt("Z"));
    }

    @Test
    public void asStringReturnsCorrectString() {
        assertEquals("A", Coord.asString(1));
        assertEquals("Z", Coord.asString(26));
    }

    @Test
    public void getCoordStringReturnsCorrectString() {
        Coord coord = new Coord(1, 2);
        assertEquals("(A, 2)", coord.getCoordString());
    }

    @Test
    public void constructACoordFromARowColString() {
        Coord coord = new Coord("B3");
        assertEquals(2, coord.row);
        assertEquals(3, coord.col);
    }

    @Test
    public void isEqualReturnsTrue() {
        Coord coord = new Coord(1, 2);
        Coord coord2 = new Coord(1, 2);
        assertTrue(coord.isEqual(coord2));
    }

    @Test
    public void isEqualReturnsFalse() {
        Coord coord = new Coord(1, 2);
        Coord coord2 = new Coord(3, 3);
        assertFalse(coord.isEqual(coord2));
    }

    @Test
    public void shiftByReturnsCorrectCoordinate() {
        Coord coord = new Coord(1, 2);
        Coord actual = coord.shiftBy(1, 2);
        Coord expected = new Coord(2, 4);
        assertEquals(true, expected.isEqual(actual));
    }
}
