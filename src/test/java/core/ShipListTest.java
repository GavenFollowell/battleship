package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

class ShipListTest {
    Coord coordinate = new Coord(1, 2);
    Ship ship1 = new Ship(coordinate, 3, VERTICAL, "BattleShip1");
    Coord coordinate2 = new Coord(1, 2);
    Ship ship2 = new Ship(coordinate2, 3, HORIZONTAL, "BattleShip2");
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    @Test
    public void addShipListToDatabase() {
        ShipList shipListOriginal = new ShipList();
        shipListOriginal.addShip(ship1);
        shipListOriginal.addShip(ship2);
        EntityManager em = factory.createEntityManager();
        persistShipList(em, shipListOriginal);

        long originalID = shipListOriginal.getId();

        EntityManager em2 = factory.createEntityManager();
        em2.getTransaction().begin();
        ShipList shipList2 = em2.find(ShipList.class, originalID);
        em2.close();

        assertEquals(shipListOriginal.getShips().size(), shipList2.getShips().size());
        assertEquals(
                shipListOriginal.getShips().getFirst().getName(),
                shipList2.getShips().getFirst().getName());
    }

    @Test
    public void addEmptyShipListToDatabase() {
        ShipList shipListOriginal = new ShipList();
        EntityManager em = factory.createEntityManager();
        persistShipList(em, shipListOriginal);

        long originalID = shipListOriginal.getId();
        EntityManager em2 = factory.createEntityManager();
        em2.getTransaction().begin();
        ShipList shipList2 = em2.find(ShipList.class, originalID);
        em2.close();

        assertEquals(shipListOriginal.getShips().size(), shipList2.getShips().size());
        assertEquals(shipListOriginal.getShips().isEmpty(), shipList2.getShips().isEmpty());
    }

    private static void persistShipList(EntityManager em, ShipList shipListOriginal) {
        em.getTransaction().begin();
        em.persist(shipListOriginal);
        em.getTransaction().commit();
        em.close();
    }
}
