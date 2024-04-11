package dungeonmania.entities.logic;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public interface Logic {
    public boolean isActivated(GameMap map, Position prevPos);

}
