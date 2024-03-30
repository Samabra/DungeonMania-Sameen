package dungeonmania.entities.enemies.enemyMovement;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.util.Position;
import dungeonmania.entities.enemies.Spider;

// need to ask whether it's legal to have any type in the paramter when the
// abstract method has type Enemy (the super class)
// Can I force a typecast if I can't do the above? Or is it bad style
public class MoveSpider implements EnemyMovement {
    public void apply(Game game, Enemy enemy) {
        Spider spider = (Spider) enemy;
        Position nextPos = spider.getNextPosition();
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            spider.setForward(!spider.getForward());
            spider.updateNextPosition();
            spider.updateNextPosition();
        }
        nextPos = spider.getNextPosition();
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.size() == 0
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(), spider))) {
            game.getMap().moveTo(spider, nextPos);
            spider.updateNextPosition();
        }
    }
}
