// Raihan and Jonah
package core;

public class Cell {
    private boolean shot;
    private boolean ship;

    public Cell() {
        this.shot = false;
        this.ship = false;
    }

    public Cell(boolean ship) {
        this.ship = ship;
        this.shot = false;
    }

    public boolean isEmpty() {
        return !this.hasShip();
    }

    // the cell has been shot
    public boolean hasBeenShot() {
        return this.shot;
    }

    // the cell contains a ship
    public boolean hasShip() {
        return this.ship;
    }

    // the shot on this cell resulted in a hit
    public boolean cellIsHit() {
        if (this.hasBeenShot() && this.hasShip()) {
            return true;
        }
        return false;
    }

    // shot on this cell resulted in a miss
    public boolean cellIsMiss() {
        if (this.hasBeenShot() && !this.hasShip()) {
            return true;
        }
        return false;
    }

    // Mark the cell as shot
    public void setAsShot() {
        this.shot = true;
    }

    // Place a ship in the cell
    public void setAsShip() {
        this.ship = true;
    }

    // Reset the cell to its initial state
    public void reset() {
        this.shot = false;
        this.ship = false;
    }
}
