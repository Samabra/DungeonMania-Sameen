package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.logic.activateStrategy.ActivateFactory;
import dungeonmania.entities.logic.activateStrategy.ActivateStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LogicBomb extends Entity implements InventoryItem, Logic {
    public enum State {
        SPAWNED, INVENTORY, PLACED
    }

    public static final int DEFAULT_RADIUS = 1;
    private State state;
    private ActivateStrategy activateStrategy;
    private int radius;

    public LogicBomb(Position pos, int radius, String logic) {
        super(pos);
        activateStrategy = ActivateFactory.factory(logic);
        state = State.SPAWNED;
        this.radius = radius;
    }

    @Override
    public synchronized boolean isActivated(GameMap map, Position prevPos) {
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.remove(null);
        // should only store wires and switches
        List<Logic> adjEntities = new ArrayList<>();

        adjPosList.stream().forEach(node -> {
            // there should only be one logical entity per tile
            List<Entity> entities = map.getEntities(node).stream()
                    .filter(e -> (e instanceof Wire || e instanceof Switch)).collect(Collectors.toList());
            if (entities.size() != 0) {
                adjEntities.add((Logic) entities.get(0));
            }
        });

        boolean activated = activateStrategy.apply(map, adjEntities, getPosition());
        if (activated)
            explode(map);
        return activated;
    }

    public void onPutDown(GameMap map, Position p) {
        setPosition(p);
        map.addEntity(this);
        this.state = State.PLACED;
        if (isActivated(map, p)) {
            explode(map);
        }
    }

    public void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                List<Entity> entities = map.getEntities(new Position(i, j));
                entities = entities.stream().filter(e -> !(e instanceof Player)).collect(Collectors.toList());
                for (Entity e : entities)
                    map.destroyEntity(e);
            }
        }
    }
}
