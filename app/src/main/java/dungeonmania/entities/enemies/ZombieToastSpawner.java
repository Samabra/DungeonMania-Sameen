package dungeonmania.entities.enemies;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Destroyable;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToastSpawner extends Entity implements Interactable, Destroyable {
    public static final int DEFAULT_SPAWN_INTERVAL = 0;

    public ZombieToastSpawner(Position position, int spawnInterval) {
        super(position);
    }

    public void spawn(Game game) {
        game.getEntityFactory().spawnZombie(game, this);
    }

    @Override
    public void onDestroy(GameMap map) {
        Game g = map.getGame();
        g.unsubscribe(getId());
    }


    @Override
    public boolean isInteractable(Player player) {
        List<Position> positions = getPosition().getCardinallyAdjacentPositions();
        for (Position place: positions) {
            if (place.equals(player.getPosition())
                && player.hasWeapon()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return false;
    }
}
