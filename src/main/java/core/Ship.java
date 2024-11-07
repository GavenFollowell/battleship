package core;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    private int startRow;
    private int startCol;
    private int size;
    private Direction direction;
    private String name;
    private List<Coord> coordList;

    public Ship(Coord coordinate, int size, Direction direction, String name) {
        this.startRow = coordinate.row;
        this.startCol = coordinate.col;
        this.size = size;
        this.direction = direction;
        this.name = name;
        this.coordList = getCoordList();
    }

    public int getSize() {
        return size;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public boolean containsCoord(Coord coord) {
        for (Coord c : coordList) {
            if (coord.isEqual(c)) {
                return true;
            }
        }
        return false;
    }

    public List<Coord> getCoordList() {
        List<Coord> coordList = new ArrayList<Coord>();
        if (direction == Direction.HORIZONTAL) {
            for (int i = 0; i < this.size; i++) {
                coordList.add(new Coord(startRow, startCol + i));
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                coordList.add(new Coord(startRow + i, startCol));
            }
        }
        return coordList;
    }

    public boolean isSunk(Grid grid) {
        for (Coord coord : coordList) {
            if (!(grid.get(coord).cellIsHit())) {
                return false;
            }
        }
        return true;
    }

    public boolean isOverlapping(Ship other) {
        for (Coord coord : other.getCoordList()) {
            if (this.containsCoord(coord)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOnGrid(Grid g) {
        for (Coord coord : this.getCoordList()) {
            if (coord.row < 1 || coord.row > g.numRows()) return false;
            if (coord.col < 1 || coord.col > g.numCols()) return false;
        }
        return true;
    }
}
