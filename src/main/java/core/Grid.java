package core;

import java.util.List;

public class Grid {
    private final int rows;
    private final int cols;

    private Cell[][] grid;

    private List<Cell> shipList;
    private List<Cell> chosenCells;

    public Grid(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        grid = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell();
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

    public boolean isValid(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
