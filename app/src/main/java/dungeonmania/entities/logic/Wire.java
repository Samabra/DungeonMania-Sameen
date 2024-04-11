package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.logic.activateStrategy.ActivateFactory;
import dungeonmania.entities.logic.activateStrategy.ActivateStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Wire extends Entity implements Logic {
    private ActivateStrategy activateStrategy;
    // private List<Logic> adjEntities = new ArrayList<>();

    public Wire(Position pos) {
        super(pos);
        this.activateStrategy = ActivateFactory.factory("or");
    }

    @Override
    public boolean isActivated(GameMap map, Position prevPos) {
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.remove(prevPos);
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

    public void searchEntity(GameMap map, Position prevPos) {

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
            } else if (enitity instanceof Switch && ((Switch) enitity).isActivated()) {
                ((Switch) enitity).searchEntity(map, getPosition());
            } else {
                enitity.isActivated(map, getPosition());
            }
        });
    }
}
