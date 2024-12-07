package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final int rows;
    private final int cols;

    private Cell[][] grid;

    private final List<Ship> shipList = new ArrayList<>();
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

        this.shipList.addAll(shipList);

        markAllShipCells();
    }

    public Grid(int rows, int cols) {
        this(rows, cols, new ArrayList<>());
    }

    private void markAllShipCells() {
        for (Ship ship : shipList) {
            markShipCells(ship);
        }
    }

    private void markShipCells(Ship ship) {
        for (Coord coord : ship.getCoordList()) {
            getCell(coord).setAsShip();
        }
    }

    public Cell getCell(Coord coordinate) {
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
        Ship ship1 = new Ship(new Coord(1, 2), 3, VERTICAL, "Submarine");
        Ship ship2 = new Ship(new Coord(5, 1), 5, HORIZONTAL, "Carrier");
        Ship ship3 = new Ship(new Coord(1, 5), 3, VERTICAL, "Destroyer");
        return List.of(ship1, ship2, ship3);
    }

    public static Grid defaultGrid() {
        return new Grid(5, 5, defaultShipsFor5x5());
    }

    public boolean isValid(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void addShip(Ship ship) {
        shipList.add(ship);
        markShipCells(ship);
    }

    public boolean allShipsAreSunk() {
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                if (!this.getCell(coord).cellIsHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public boolean isShipSunk(Ship ship) {
        for (Coord coord : ship.getCoordList()) {
            if (!(getCell(coord).cellIsHit())) {
                return false;
            }
        }
        return true;
    }

    public boolean isShipOnGrid(Ship ship) {
        for (Coord coord : ship.getCoordList()) {
            if (coord.row < 1 || coord.row > numRows()) return false;
            if (coord.col < 1 || coord.col > numCols()) return false;
        }
        return true;
    }

    public void shoot(Coord coordinate) {
        Cell target = getCell(coordinate);
        if (!target.hasBeenShot()) {
            target.setAsShot();
        } else {
            return;
        }
    }
}
