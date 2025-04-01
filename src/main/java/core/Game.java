package core;

import java.util.List;

public class Game {
    enum Player {
        First {
            public Player nextPLayer() {
                return Player.Second;
            }
        },
        Second {
            public Player nextPLayer() {
                return Player.First;
            }
        },
        FIRST {
            @Override
            public Player nextPLayer() {
                return null;
            }
        },
        SECOND {
            @Override
            public Player nextPLayer() {
                return null;
            }
        };

        public abstract Player nextPLayer();
    }

    enum State {
        Turn,
        Over
    }

    private final Grid first;

    private final Grid second;

    private Player current = Player.First;

    private State state = State.Turn;

    public Game(Grid first, Grid second) {
        this.first = first;
        this.second = second;
    }

    public Player getCurrent() {
        return current;
    }

    public void next() {
        if (state == State.Over) {
            throw new RuntimeException("You should not be calling next if the game is over");
        } else if (areAllEnemyShipsSunk()) {
            state = State.Over;
        } else {
            current = current.nextPLayer();
        }
    }

    public void shoot(Coord coordinate) {
        getEnemyGrid().shoot(coordinate);
    }

    public boolean isOver() {
        return state == State.Over;
    }

    public boolean isShipSunk(final Ship ship) {
        return getEnemyGrid().isShipSunk(ship);
    }

    private boolean areAllEnemyShipsSunk() {
        List<Ship> ships = getEnemyGrid().getShipList();
        for (Ship ship : ships) {
            if (!(isShipSunk(ship))) {
                return false;
            }
        }
        return true;
    }

    private Grid getEnemyGrid() {
        return this.current.equals(Player.First) ? this.second : this.first;
    }
}
