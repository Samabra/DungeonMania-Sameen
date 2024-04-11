package dungeonmania.entities.enemies;

import java.util.List;
import java.util.stream.Collectors;

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
    private boolean allied = false;
    private int mindControlDuration;
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
        return allied;
    }

    public void mindControl(int duration) {
        mindControlDuration = duration;
        allied = true;
    }

    public void onTickMindControl() {
        mindControlDuration--;
        if (allied && mindControlDuration == 0) {
            allied = false;
        }
    }


    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (allied)
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
                    return player.countEntityOfType(Treasure.class) >= bribeAmount
                            && !allied;
                }
            }
        }
        return true;
        //return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    /**
     * bribe the merc
     */
    public void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }
        allied = true;

    }

    @Override
    public void move(Game game) {
        getEnemyMovement().apply(game, this);
    }


    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
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
