package dungeonmania.entities.enemies.enemyMovement;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.ZombieToast;

public class MoveZombieToast implements EnemyMovement {
    @Override
    public void apply(Game game, Enemy enemy) {
        ZombieToast zombieToast = (ZombieToast) enemy;
        Position nextPos;
        GameMap map = game.getMap();
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(),
                    zombieToast.getPosition());

            Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(zombieToast.getPosition(), Direction.RIGHT)
                    : Position.translateBy(zombieToast.getPosition(), Direction.LEFT);
            Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(zombieToast.getPosition(), Direction.UP)
                    : Position.translateBy(zombieToast.getPosition(), Direction.DOWN);
            Position offset = zombieToast.getPosition();
            if (plrDiff.getY() == 0 && map.canMoveTo(enemy, moveX))
                offset = moveX;
            else if (plrDiff.getX() == 0 && map.canMoveTo(enemy, moveY))
                offset = moveY;
            else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
                if (map.canMoveTo(enemy, moveX))
                    offset = moveX;
                else if (map.canMoveTo(enemy, moveY))
                    offset = moveY;
                else
                    offset = zombieToast.getPosition();
            } else {
                if (map.canMoveTo(enemy, moveY))
                    offset = moveY;
                else if (map.canMoveTo(enemy, moveX))
                    offset = moveX;
                else
                    offset = zombieToast.getPosition();
            }
            nextPos = offset;
        } else {
            List<Position> pos = zombieToast.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).collect(Collectors.toList());
            if (pos.size() == 0) {
                nextPos = zombieToast.getPosition();
            } else {
                nextPos = pos.get(zombieToast.randGen(pos.size()));
            }
        }
        game.getMap().moveTo(enemy, nextPos);
    }
}
