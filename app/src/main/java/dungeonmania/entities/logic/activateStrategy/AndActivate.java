package dungeonmania.entities.logic.activateStrategy;

import java.util.List;

import dungeonmania.entities.logic.Logic;
import dungeonmania.entities.logic.Switch;
import dungeonmania.entities.logic.Wire;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class AndActivate implements ActivateStrategy {
    @Override
    public synchronized boolean apply(GameMap map, List<Logic> logicEntities, Position currPos) {
        System.out.println(logicEntities.size());
        if (logicEntities.size() < 2)
            return false;

        boolean isActivated = true;
        for (Logic entity : logicEntities) {
            if (entity instanceof Wire || entity instanceof Switch) {
                isActivated = isActivated && entity.isActivated(map, currPos);
            }
        }

        return isActivated;
    }

}
