package ui;

import core.Cell;

public class DefualtConvert implements Convert {
    public DefualtConvert() {}

    @Override
    public String convert(Cell cell) {
        if (cell.cellIsHit()) {
            return "X";
        } else if (cell.hasShip()) {
            return "~";
        } else if (cell.cellIsMiss()) {
            return "*";
        } else if (cell.isEmpty()) {
            return "0";
        }
        return "";
    }
}
