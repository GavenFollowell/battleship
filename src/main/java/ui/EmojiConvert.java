package ui;

import core.Cell;

public class EmojiConvert implements Convert {
    public EmojiConvert() {}

    @Override
    public String convert(Cell cell) {
        if (cell.cellIsHit()) {
            return "☠";
        } else if (cell.hasShip()) {
            return "\uD83D\uDEA2";
        } else if (cell.cellIsMiss()) {
            return "\uD83D\uDEAB";
        } else if (cell.isEmpty()) {
            return "\uD83C\uDF0A";
        }
        return "";
    }
}
