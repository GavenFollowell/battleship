package core;

import db.CellArrayAttributeConverter;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Grid is the cells marked as rows and cols. Adds ship functionality. */
@Entity
@Table(name = "grid")
public class Grid implements Bounding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gridId")
    private long id;

    @Column(name = "row")
    private int rows;

    @Column(name = "cols")
    private int cols;

    @Convert(converter = CellArrayAttributeConverter.class)
    @Column(name = "cells", columnDefinition = "TEXT")
    private Cell[][] cells;

    @ManyToOne
    @JoinColumn(name = "ship_list_id", referencedColumnName = "id")
    private ShipList shipList;

    public Grid() {
        this.rows = 0;
        this.cols = 0;
    }

    public Grid(final int rows, final int cols, final List<Ship> shipList) {
        this.rows = rows;
        this.cols = cols;

        cells = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell();
            }
        }

        this.shipList = new ShipList(shipList);
    }

    public Grid(final int rows, final int cols) {
        this(rows, cols, new ArrayList<>());
    }

    Cell getCell(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return cells[row][col];
    }

    public CellStatus getStatus(final Coord coordinate) {
        final Cell cell = getCell(coordinate);
        for (final Ship ship : shipList.getShips()) {
            if (ship.containsCoord(coordinate)) {
                return cell.hasBeenShot() ? CellStatus.SHIP_HIT : CellStatus.SHIP_UNREVEALED;
            }
        }
        return cell.hasBeenShot() ? CellStatus.EMPTY : CellStatus.UNKNOWN;
    }

    public int numRows() {
        return rows;
    }

    public int numCols() {
        return cols;
    }

    /**
     * Attempts to add a ship if it's within bounds and doesn't overlap existing ships.
     *
     * @param ship the ship to add
     */
    public void addShip(final Ship ship) {
        if (!ship.isWithinBounds(this)) {
            return;
        }
        shipList.addShip(ship);
    }

    public boolean allShipsAreSunk() {
        for (final Ship ship : shipList.getShips()) {
            final List<Coord> coords = ship.getCoordList();
            for (final Coord coord : coords) {
                if (!this.getStatus(coord).equals(CellStatus.SHIP_HIT)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Ship> getShipList() {
        return shipList.getShips();
    }

    public boolean isShipSunk(final Ship ship) {
        for (final Coord coord : ship.getCoordList()) {
            if (!this.getStatus(coord).equals(CellStatus.SHIP_HIT)) {
                return false;
            }
        }
        return true;
    }

    public Optional<Ship> isShipSunk(Coord coordinate, Boolean onlyReturnSunk) {
        Optional<Ship> optionalShip = Optional.empty();
        for (Ship ship : this.getShipList()) {
            if (ship.containsCoord(coordinate)) {
                optionalShip = Optional.of(ship);
                break;
            }
        }
        if (optionalShip.isEmpty()) return optionalShip;
        for (final Coord coord : optionalShip.get().getCoordList()) {
            if (!this.getStatus(coord).equals(CellStatus.SHIP_HIT)) {
                if (!onlyReturnSunk) {
                    return optionalShip;
                } else {
                    return Optional.empty();
                }
            }
        }
        return optionalShip;
    }

    public void shoot(final Coord coordinate) {
        final Cell shipCell = getCell(coordinate);
        final CellStatus targetStatus = getStatus(coordinate);
        if (!targetStatus.equals(CellStatus.SHIP_HIT)) {
            shipCell.setAsShot();
        }
    }

    public long getId() {
        return id;
    }
}
