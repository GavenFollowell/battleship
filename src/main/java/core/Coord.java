package core;

import jakarta.persistence.*;
import java.util.List;

// @Entity
public class Coord {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private static final List<String> ALPHABET =
            List.of(
                    " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public final int row;
    public final int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coord() {
        row = 0;
        col = 0;
    }

    public static int asInt(String row) {
        return ALPHABET.indexOf(row);
    }

    public static String asString(int row) {
        return ALPHABET.get(row);
    }

    public String getCoordString() {
        return asString(row) + col;
    }

    public Coord(String rowCol) {
        String[] coords = rowCol.split("");
        this.row = asInt(coords[0]);
        this.col = Integer.parseInt(coords[1]);
    }

    public boolean isEqual(Coord other) {
        return this.col == other.col && this.row == other.row;
    }

    public Coord shiftBy(int verticalDistance, int horizontalDistance) {
        return new Coord(this.row + verticalDistance, this.col + horizontalDistance);
    }

    public Boolean isWithin(Bounding bounding) {
        final int numRows = bounding.numRows();
        final int numCols = bounding.numCols();
        return row - 1 >= 0 && row - 1 < numRows && col - 1 >= 0 && col - 1 < numCols;
    }
}
