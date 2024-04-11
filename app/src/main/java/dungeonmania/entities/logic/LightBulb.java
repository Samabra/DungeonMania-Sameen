package dungeonmania.entities.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.logic.activateStrategy.ActivateFactory;
import dungeonmania.entities.logic.activateStrategy.ActivateStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class LightBulb extends Entity implements Logic {
    private ActivateStrategy activateStrategy;
    private boolean isOn = false;

    // // stores whether adjNodes are activated or not
    // private List<Boolean> adjNodes = new ArrayList<Boolean>();

    public LightBulb(Position pos, String logic) {
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

        this.isOn = activateStrategy.apply(map, adjEntities, getPosition());
        return this.isOn;
    }

    public boolean isOn() {
        return this.isOn;
    }

    // FIXME: need to think about how to change it to light_bulb_on
}
