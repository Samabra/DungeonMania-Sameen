package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Movable;
import dungeonmania.entities.Overlappable;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity implements Movable, Overlappable, Logic {
    private boolean activated;
    private List<Bomb> bombs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    public void subscribe(Bomb b) {
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (activated) {
            bombs.stream().forEach(b -> b.notify(map));
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            setActivated(map, true);
            bombs.stream().forEach(b -> b.notify(map));

            // FIXME: use this as a way to find logic entities
            searchEntity(map, null);
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            setActivated(map, false);
            searchEntity(map, null);
        }
    }

    public synchronized void searchEntity(GameMap map, Position prevPos) {

        List<Logic> logicEntities = new ArrayList<>();
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.remove(prevPos);
        adjPosList.stream().forEach(node -> {
            // there should only be one logical entity per tile
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof Logic))
                    .collect(Collectors.toList());
            if (entities.size() != 0) {
                Logic logicEntity = (Logic) entities.get(0);
                logicEntities.add(logicEntity);
            }
        });

        logicEntities.stream().forEach(enitity -> {
            if (enitity instanceof Wire) {
                ((Wire) enitity).searchEntity(map, getPosition());
                // TODO: might need to check below condition
            } else if (enitity instanceof Switch && ((Switch) enitity).isActivated()) {
                ((Switch) enitity).searchEntity(map, getPosition());
            } else {
                enitity.isActivated(map, getPosition());
            }
        });
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(GameMap map, Boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean isActivated(GameMap map, Position prevPos) {
        return activated;
    }

}
