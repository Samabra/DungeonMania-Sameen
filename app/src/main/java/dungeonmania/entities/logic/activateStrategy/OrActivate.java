package dungeonmania.entities.logic.activateStrategy;

import java.util.List;

import dungeonmania.entities.logic.Logic;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class OrActivate implements ActivateStrategy {
    @Override
    public boolean apply(GameMap map, List<Logic> logicEntities, Position currPos) {
        boolean isActivated = false;
        for (Logic entity : logicEntities) {
            isActivated = isActivated || entity.isActivated(map, currPos);
        }

        return isActivated;
    }

}
