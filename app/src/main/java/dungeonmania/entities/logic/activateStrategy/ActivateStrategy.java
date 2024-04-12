package dungeonmania.entities.logic.activateStrategy;

import java.util.List;

import dungeonmania.entities.logic.Logic;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public interface ActivateStrategy {
    public boolean apply(GameMap map, List<Logic> logicEntities, Position currPos);
}
