package dungeonmania.entities.enemies.enemyMovement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;

public interface EnemyMovement {
    public void apply(Game game, Enemy enemy);
}
