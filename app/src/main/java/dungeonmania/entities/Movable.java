package dungeonmania.entities;

import dungeonmania.map.GameMap;

public interface Movable {
    public void onMovedAway(GameMap map, Entity entity);

}
