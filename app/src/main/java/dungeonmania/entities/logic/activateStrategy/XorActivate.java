package dungeonmania.entities.logic.activateStrategy;

import java.util.List;

import dungeonmania.entities.logic.Logic;
import dungeonmania.entities.logic.Switch;
import dungeonmania.entities.logic.Wire;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class XorActivate implements ActivateStrategy {
    @Override
    public boolean apply(GameMap map, List<Logic> logicEntities, Position currPos) {

        int activatedCount = 0;
        for (Logic entity : logicEntities) {
            if (entity instanceof Wire || entity instanceof Switch && entity.isActivated(map, currPos)) {
                activatedCount++;
            }
        }

        if (activatedCount != 1)
            return false;

        return true;
    }

}
