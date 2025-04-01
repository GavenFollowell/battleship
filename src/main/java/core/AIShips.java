package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AIShips is an AI that places ships on an empty grid. This Class is Used with the {@link Grid}
 * class and {@link ShipSpec} class in order to function
 *
 * @link grid hold size
 * @link ships is a list of ship names
 * @link shipSpecs an array of ship sizes
 */
class AIShips {
    private final Grid grid;
    private final List<Ship> ships;
    private final ShipSpec[] shipSpecs;

    /**
     * Creates an instance of the AIShips class
     *
     * @param grid is an empty grid that should be filled with ships
     * @param shipSpecs is a list of ShipSpec to provide name and size of ships
     */
    public AIShips(final Grid grid, final ShipSpec... shipSpecs) {
        this.grid = grid;
        this.shipSpecs = shipSpecs.clone();
        this.ships = new ArrayList<>();
        this.checkShipSizes();
    }

    /**
     * AI function, it places the ships on the empty grid provided. Must be called in order to place
     * ships.
     */
    public void setShips() {
        final int maxNumberOfTries = 10;
        int currentShip = 0;
        int tries = 0;
        while (currentShip != shipSpecs.length) {
            final Ship newShip = getShip(shipSpecs[currentShip]);
            if (newShip.isWithinBounds(grid) && !conflicts(newShip)) {
                ships.add(newShip);
                currentShip += 1;
                tries = 0;
            }
            tries++;
            if (tries == maxNumberOfTries) {
                currentShip = 0;
                tries = 0;
                ships.clear();
            }
        }
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private void checkShipSizes() {
        for (ShipSpec shipSpec : shipSpecs) {
            if (shipSpec.size > grid.numRows() || shipSpec.size > grid.numCols()) {
                throw new RuntimeException("INVALID SHIP SIZES GIVEN");
            }
        }
    }

    private Ship getShip(final ShipSpec shipSpec) {
        final Random random = new Random();
        final int row = getRow(random);
        final int col = getCol(random);
        final Ship.Direction direction = getDirection(random);
        return new Ship(new Coord(row, col), shipSpec.size, direction, shipSpec.name);
    }

    private int getRow(final Random random) {
        return random.nextInt(0, grid.numRows());
    }

    private int getCol(final Random random) {
        return random.nextInt(0, grid.numCols());
    }

    private static Ship.Direction getDirection(final Random random) {
        return random.nextBoolean() ? Ship.Direction.VERTICAL : Ship.Direction.HORIZONTAL;
    }

    private boolean conflicts(Ship newShip) {
        for (final Ship ship : ships) {
            if (newShip.isOverlapping(ship)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the amount of ships on the AI's grid
     */
    public int getShipCount() {
        return ships.size();
    }

    /**
     * @return the list of ships on the grid
     */
    public List<Ship> getShips() {
        return ships;
    }
}
