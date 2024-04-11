package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.entities.logic.activateStrategy.ActivateFactory;
import dungeonmania.entities.logic.activateStrategy.ActivateStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SwitchDoor extends Entity implements Logic {
    private ActivateStrategy activateStrategy;

    public SwitchDoor(Position pos, String logic) {
        super(pos);
        activateStrategy = ActivateFactory.factory(logic);
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

        return activateStrategy.apply(map, adjEntities, getPosition());
    }

    // @Override
    // public void onOverlap(GameMap map, Entity entity) {
    //     if (!(entity instanceof Player))
    //         return;

    //     Player player = (Player) entity;
    //     Inventory inventory = player.getInventory();
    //     Key key = inventory.getFirst(Key.class);

    //     if (hasKey(player)) {
    //         inventory.remove(key);
    //         open();
    //     }
    // }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        // FIXME: need to check if spider can move onto switchdoor
        if (isActivated(map, null) || entity instanceof Spider) {
            return true;
        }
        return false;
    }
}
