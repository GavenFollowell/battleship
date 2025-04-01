package core;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShipList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Ship> ships = new ArrayList<>();

    protected ShipList() {}

    public ShipList(List<Ship> ships) {
        this.ships.addAll(ships);
    }

    /**
     * Adds a ship to the list only if it does not overlap with existing ships
     *
     * @param ship the ship to add
     * @return true if successfully added, false otherwise
     *     <p>✅ CHANGED: Now returns a boolean and checks for overlaps using isOverlapping
     */
    public boolean addShip(Ship ship) {
        if (isOverlapping(ship)) {
            return false;
        }
        ships.add(ship);
        return true;
    }

    /**
     * Checks if the given ship overlaps with any existing ships
     *
     * <p>✅ ADDED: Helper method to assist in validating ship overlap
     */
    public boolean isOverlapping(Ship newShip) {
        for (Ship existing : ships) {
            if (existing.isOverlapping(newShip)) {
                return true;
            }
        }
        return false;
    }

    public List<Ship> getShips() {
        return new ArrayList<>(ships);
    }

    public long getId() {
        return id;
    }
}
