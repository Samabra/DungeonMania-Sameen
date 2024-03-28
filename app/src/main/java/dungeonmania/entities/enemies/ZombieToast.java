package dungeonmania.entities.enemies;

import java.util.Random;

import dungeonmania.Game;
import dungeonmania.entities.enemies.enemyMovement.MoveZombieToast;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    private Random randGen = new Random();

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
        setEnemyMovement(new MoveZombieToast());
    }

    @Override
    public void move(Game game) {
        getEnemyMovement().apply(game, this);
    }

    public int randGen(int size) {
        return randGen.nextInt(size);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
