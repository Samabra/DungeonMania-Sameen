package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.enemies.enemyMovement.MoveMercenary;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.inventory.Inventory;

public class Mercenary extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private double allyAttack;
    private double allyDefence;
    private boolean bribed = false;
    private boolean mindControlled = false;
    private int mindControlDuration = 0;
    private boolean isAdjacentToPlayer = false;

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack);
        setEnemyMovement(new MoveMercenary());
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
    }

    public boolean isAllied() {
        return mindControlled || bribed;
    }

    public void mindControl(int duration) {
        mindControlDuration = duration;
        mindControlled = true;
    }

    public void onTickMindControl() {
        if (mindControlDuration > 0) {
            mindControlDuration--;
        }
        if (mindControlDuration == 0) {
            mindControlled = false;
        }
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (isAllied())
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     * @param player
     * @return
     */

    @Override
    public boolean isInteractable(Player player) {
        Inventory inventory = player.getInventory();
        if (inventory.itemExists(Sceptre.class)) {
            return true;
        }
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - bribeRadius; i <= x + bribeRadius; i++) {

            for (int j = y - bribeRadius; j <= y + bribeRadius; j++) {
                if (player.comparePosition(new Position(i, j))) {
                    return player.countEntityOfType(Treasure.class) >= bribeAmount && !(mindControlled || bribed);
                }
            }
        }
        return false;
    }
    
    public void adjacentToPlayer(Player player) {
        if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), getPosition())) {
            isAdjacentToPlayer = true;
        }
    }
    /**
     * bribe the merc
     */
    public void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }
        this.bribed = true;

    }

    @Override
    public void move(Game game) {
        getEnemyMovement().apply(game, this);
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!isAllied())
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public boolean getIsAdjacentToPlayer() {
        return this.isAdjacentToPlayer;
    }

    public void setIsAdjacentToPlayer(boolean isAdjacentToPlayer) {
        this.isAdjacentToPlayer = isAdjacentToPlayer;
    }
}
