package dungeonmania.entities.logic.activateStrategy;

import java.util.List;

import dungeonmania.entities.logic.Conductor;
import dungeonmania.entities.logic.Logic;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class CoandActivate implements ActivateStrategy {
    // private static int numTickActivated = 0;

    // @Override
    // public synchronized boolean apply(GameMap map, List<Logic> logicEntities, Position currPos) {
    //     if (logicEntities.size() < 2)
    //         return false;

    //     boolean isActivated = true;
    //     int counter = 0;
    //     for (Logic entity : logicEntities) {
    //         isActivated = entity.isActivated(map, currPos);
    //         if (isActivated) {
    //             counter++;
    //         }
    //     }
    //     if (counter == 0) {
    //         // reset to 0 if all adjacent conductors are deactivated
    //         numTickActivated = 0;
    //         return false;
    //     } else if (counter != logicEntities.size() || numTickActivated > 0) {
    //         numTickActivated++;
    //         return false;
    //     }

    //     return true;
    // }
    @Override
    public synchronized boolean apply(GameMap map, List<Logic> logicEntities, Position currPos) {
        if (logicEntities.size() < 2)
            return false;

        int activatedCounter = 0;
        for (Logic entity : logicEntities) {
            if (((Conductor) entity).prevActivated()) {
                // System.out.println("YESS");
                activatedCounter++;
            }
        }
        // System.out.println("1");
        // System.out.println("ACTIVATED COUNTER " + activatedCounter);
        if (activatedCounter != 0 && activatedCounter < logicEntities.size()) {
            return false;
        }
        // System.out.println("2");

        boolean isActivated = true;
        for (Logic entity : logicEntities) {
            if (entity instanceof Conductor) {
                // System.out.println(entity.getClass() + " " + isActivated);
                isActivated = isActivated && entity.isActivated(map, currPos);
            }
        }
        // System.out.println("FINAL: " + isActivated);

        return isActivated;
    }

}
