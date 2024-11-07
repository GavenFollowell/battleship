package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AIShipsTest {

    @Test
    public void canPlaceShipsFromSetSizes() {
        Grid grid = new Grid(8, 8);
        ShipSpec[] shipSpecs =
                new ShipSpec[] {
                    new ShipSpec("Carrier", 5), new ShipSpec("Ship", 5), new ShipSpec("Ship", 5)
                };
        AIShips aiShips = new AIShips(grid, shipSpecs);
        aiShips.setShips();
        assertEquals(3, aiShips.getShipCount());
    }

    @Test
    public void throwsErrorIfShipSizeIsTooBig() {
        try {
            Grid grid = new Grid(8, 8);
            ShipSpec[] shipSpecs =
                    new ShipSpec[] {
                        new ShipSpec("Carrier", 5),
                        new ShipSpec("BattleShip1", 7),
                        new ShipSpec("BattleShip2", 8),
                        new ShipSpec("BattleShip3", 9)
                    };
            AIShips aiShips = new AIShips(grid, shipSpecs);
        } catch (RuntimeException e) {
            assertEquals("INVALID SHIP SIZES GIVEN", e.getMessage());
        }
    }
}
