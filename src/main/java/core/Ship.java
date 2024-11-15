package core;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    private Coord startcoordinate;
    private int size;
    private Direction direction;
    private String name;
    private List<Coord> coordList;

    public Ship(Coord coordinate, int size, Direction direction, String name) {
        this.startcoordinate = coordinate;
        this.size = size;
        this.direction = direction;
        this.name = name;
        this.coordList = genCoordList();
    }

    public int getSize() {
        return size;
    }

    public int getStartRow() {
        return startcoordinate.row;
    }

    public int getStartCol() {
        return startcoordinate.col;
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

    private List<Coord> genCoordList() {
        List<Coord> coordList = new ArrayList<Coord>();
        if (direction == Direction.HORIZONTAL) {
            for (int i = 0; i < this.size; i++) {
                coordList.add(startcoordinate.shiftBy(0, i));
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                coordList.add(startcoordinate.shiftBy(i, 0));
            }
        }
        return coordList;
    }

    public List<Coord> getCoordList() {
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
        for (Coord coord : other.genCoordList()) {
            if (this.containsCoord(coord)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOnGrid(Grid g) {
        for (Coord coord : this.genCoordList()) {
            if (coord.row < 1 || coord.row > g.numRows()) return false;
            if (coord.col < 1 || coord.col > g.numCols()) return false;
        }
        return true;
    }
}
