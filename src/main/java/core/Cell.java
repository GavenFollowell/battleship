// Raihan and Jonah
package core;

public class Cell {
    private boolean shot;
    private boolean ship;
    private boolean hit;
    private boolean miss;

    public Cell() {
        this.shot = false;
        this.ship = false;
        this.hit = false;
        this.miss = false;
    }

    public Cell(boolean ship) {
        this.ship = ship;
        this.shot = false;
        this.hit = false;
        this.miss = false;
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
        return this.hit;
    }

    // shot on this cell resulted in a miss
    public boolean cellIsMiss() {
        return this.miss;
    }

    // shot and hit if it contains a ship
    public void hit() {
        if (!hasBeenShot() && hasShip()) { // Ensure shot hasn't been made yet
            setAsShot();
            setAsHit();
        }
    }

    // Handle a miss on the cell
    public void miss() {
        if (!hasBeenShot() && !hasShip()) { // Ensure shot hasn't been made yet
            setAsShot();
            setAsMiss();
        }
    }

    // Mark the cell as shot
    public void setAsShot() {
        this.shot = true;
    }

    // Place a ship in the cell
    public void setAsShip() {
        this.ship = true;
    }

    // Mark the cell as a miss
    public void setAsMiss() {
        this.miss = true;
    }

    // Mark the cell as a hit
    public void setAsHit() {
        this.hit = true;
    }

    // Reset the cell to its initial state
    public void reset() {
        this.shot = false;
        this.ship = false;
        this.hit = false;
        this.miss = false;
    }
}
