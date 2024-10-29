package core;


import java.util.ArrayList;
import java.util.List;

class AIShips {

    private int gridRows;
    private int gridCols;
    private List<Ship> ships;
    private int[][] grid;

    public AIShips(int gridRows, int gridCols) {
        this.gridRows = gridRows;
        this.gridCols = gridCols;
        this.ships = new ArrayList<>();
        this.grid = new int[gridRows][gridCols];
    }

    public boolean addShip(Ship ship) {
        if (isWithinGrid(ship) && !isOverlapping(ship)) {
            placeShipOnGrid(ship);
            ships.add(ship);
            return true;
        }
        return false;
    }

    private boolean isWithinGrid(Ship ship) {
        int x = ship.getStartRow();
        int y = ship.getStartCol();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        if (direction == Ship.Direction.HORIZONTAL) {
            return y + size <= gridCols;
        } else if (direction == Ship.Direction.VERTICAL) {
            return x + size <= gridRows;
        }
        return false;
    }

    private boolean isOverlapping(Ship ship) {
        int x = ship.getStartRow();
        int y = ship.getStartCol();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        for (int i = 0; i < size; i++) {
            if (direction == Ship.Direction.HORIZONTAL) {
                if (grid[x][y + i] == 1) {
                    return true;
                }
            } else if (direction == Ship.Direction.VERTICAL) {
                if (grid[x + i][y] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void placeShipOnGrid(Ship ship) {
        int x = ship.getStartRow();
        int y = ship.getStartCol();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        for (int i = 0; i < size; i++) {
            if (direction == Ship.Direction.HORIZONTAL) {
                grid[x][y + i] = 1;
            } else if (direction == Ship.Direction.VERTICAL) {
                grid[x + i][y] = 1;
            }
        }
    }

    public int getShipCount() {
        return ships.size();
    }

    public List<Ship> getShips() {
        return ships;
    }
}