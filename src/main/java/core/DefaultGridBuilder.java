package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;

import java.util.List;

public class DefaultGridBuilder {
    /**
     * the preset for a 5x5 grid
     *
     * @return the list of ships
     */
    public static List<Ship> defaultShipsFor5x5() {
        final Ship ship1 = new Ship(new Coord(1, 2), 3, VERTICAL, "Submarine");
        final Ship ship2 = new Ship(new Coord(5, 1), 5, HORIZONTAL, "Carrier");
        final Ship ship3 = new Ship(new Coord(1, 5), 3, VERTICAL, "Destroyer");
        return List.of(ship1, ship2, ship3);
    }

    /**
     * creates the default grid
     *
     * @return the preset
     */
    public static Grid defaultGrid() {
        return new Grid(5, 5, defaultShipsFor5x5());
    }
}
