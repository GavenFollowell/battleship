package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final int rows;
    private final int cols;

    private Cell[][] grid;

    private List<Ship> shipList;

    private List<Cell> chosenCells;

    public Grid(int rows, int cols, List<Ship> shipList) {

        this.rows = rows;
        this.cols = cols;

        grid = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell();
            }
        }

        this.shipList = shipList;
        markShipCells();
    }

    public Grid(int rows, int cols) {
        this(rows, cols, new ArrayList<>());
    }

    private void markShipCells() {
        for (Ship ship : shipList) {
            for (Coord coord : ship.getCoordList()) {
                get(coord).setAsShip();
            }
        }
    }

    public Cell get(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return grid[row][col];
    }

    public int numRows() {
        return rows;
    }

    public int numCols() {
        return cols;
    }

    public static List<Ship> defaultShipsFor5x5() {
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(5, 1);
        Coord c3 = new Coord(1, 5);
        Ship ship1 = new Ship(c1, 3, VERTICAL, "Submarine");
        Ship ship2 = new Ship(c2, 5, HORIZONTAL, "Carrier");
        Ship ship3 = new Ship(c3, 3, VERTICAL, "Destroyer");
        return List.of(ship1, ship2, ship3);
    }

    public static Grid defaultGrid() {
        Grid g = new Grid(5, 5, defaultShipsFor5x5());
        return g;
    }

    public boolean isValid(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean allShipsAreSunk() {
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                if (!this.get(coord).cellIsHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Ship> getShipList() {
        return shipList;
    }
}
