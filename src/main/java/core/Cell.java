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
        return this.hasBeenShot() && this.hasShip();
    }

    // shot on this cell resulted in a miss
    public boolean cellIsMiss() {
        return this.hasBeenShot() && !this.hasShip();
    }

    // Mark the cell as shot
    public void setAsShot() {
        this.shot = true;
    }

    // Place a ship in the cell
    public void setAsShip() {
        this.ship = true;
    }

    public void setAsHit() {
        this.ship = true;
        this.shot = true;
    }

    public void setAsMiss() {
        this.shot = true;
        this.ship = false;
    }

    // Reset the cell to its initial state
    public void reset() {
        this.shot = false;
        this.ship = false;
    }
}
