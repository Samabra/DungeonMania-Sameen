package dungeonmania.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.Battleable;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Mercenary;
import dungeonmania.entities.enemies.ZombieToastSpawner;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.Game;

public class Player extends Entity implements Battleable, Overlappable {
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 5.0;
    private BattleStatistics battleStatistics;
    private Inventory inventory;
    private Queue<Potion> queue = new LinkedList<>();
    private Potion inEffective = null;
    private int nextTrigger = 0;

    private int collectedTreasureCount = 0;
    private int enemiesKilledCount = 0;
    private boolean isInvincible = false;
    private boolean isInvisible = false;

    public Player(Position position, double health, double attack) {
        super(position);
        battleStatistics = new BattleStatistics(health, attack, 0, BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
                BattleStatistics.DEFAULT_PLAYER_DAMAGE_REDUCER);
        inventory = new Inventory();
    }

    public int getCollectedTreasureCount() {
        return collectedTreasureCount;
    }

    public int getEnemiesKilledCount() {
        return enemiesKilledCount;
    }

    public void incrementEnemiesKilledCount() {
        enemiesKilledCount++;
    }

    public boolean hasWeapon() {
        return inventory.hasWeapon();
    }

    public void weaponUse(Game game) {
        BattleItem weapon = getWeapon();
        if (weapon instanceof Durable) {
            ((Durable) weapon).use(game);
        }
    }

    private BattleItem getWeapon() {
        return inventory.getWeapon();
    }

    public List<String> getBuildables() {
        return inventory.getBuildables();
    }

    public boolean build(String buildable, EntityFactory factory, GameMap map) {
        InventoryItem item = inventory.checkBuildCriteria(this, true, buildable.equals("shield"), factory);
        if (item == null)
            return false;
        return inventory.add(item);
    }

    public void move(GameMap map, Direction direction) {
        this.setFacing(direction);
        map.moveTo(this, Position.translateBy(this.getPosition(), direction));
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Enemy) {
            if (mercenaryIsAllied((Enemy) entity)) {
                return;
            }
            map.initiateBattle(this, (Enemy) entity);
        }
        if (entity instanceof InventoryItem) {
            pickUp((InventoryItem) entity, map);
        }
    }

    public Entity getEntity(String itemUsedId) {
        return inventory.getEntity(itemUsedId);
    }

    private void pickUp(InventoryItem item, GameMap map) {
        if (item instanceof Bomb) {
            Bomb bomb = (Bomb) item;
            if (!bomb.spawned()) {
                return;
            }
            bomb.setState();
        }
        collectedTreasureCount++;
        inventory.add(item);
        map.destroyEntity((Entity) item);
    }

    private boolean mercenaryIsAllied(Enemy enemy) {
        return enemy instanceof Mercenary && ((Mercenary) enemy).isAllied();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void interact(Entity entity, GameMap map, Game game) {
        if (entity instanceof Mercenary) {
            if (inventory.itemExists(Sceptre.class) && !((Mercenary) entity).isAllied()) {
                Sceptre sceptre = inventory.getFirst(Sceptre.class);
                inventory.remove(sceptre);
                ((Mercenary) entity).mindControl(sceptre.getMindControlDuration());
            } else if (((Mercenary) entity).isInteractable(this) && !((Mercenary) entity).isAllied()) {
                ((Mercenary) entity).bribe(this);
            }
            ((Mercenary) entity).adjacentToPlayer(this);
        }
        if (entity instanceof ZombieToastSpawner && ((ZombieToastSpawner) entity).isInteractable(this)) {
            weaponUse(game);
            map.destroyEntity(entity);
        }
    }

    public boolean comparePosition(Position newPosition) {
        return getPosition().equals(newPosition);
    }

    public Potion getEffectivePotion() {
        return inEffective;
    }

    public <T extends InventoryItem> void use(Class<T> itemType) {
        T item = inventory.getFirst(itemType);
        if (item != null)
            inventory.remove(item);
    }

    public void use(Bomb bomb, GameMap map) {
        inventory.remove(bomb);
        bomb.onPutDown(map, getPosition());
    }

    public void triggerNext(int currentTick) {
        if (queue.isEmpty()) {
            inEffective = null;
            isInvincible = false;
            isInvisible = false;
            return;
        }
        inEffective = queue.remove();
        if (inEffective instanceof InvincibilityPotion) {
            isInvincible = true;
        } else {
            isInvisible = true;
        }
        nextTrigger = currentTick + inEffective.getDuration();
    }

    // public void changeState(PlayerState playerState) {
    //     state = playerState;
    // }

    public void use(Potion potion, int tick) {
        inventory.remove(potion);
        queue.add(potion);
        if (inEffective == null) {
            triggerNext(tick);
        }
    }

    public void onTick(int tick) {
        if (inEffective == null || tick == nextTrigger) {
            triggerNext(tick);
        }
    }

    public void remove(InventoryItem item) {
        inventory.remove(item);
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        return battleStatistics;
    }

    public <T extends InventoryItem> int countEntityOfType(Class<T> itemType) {
        return inventory.count(itemType);
    }

    public BattleStatistics applyBuff(BattleStatistics origin) {
        if (isInvincible) {
            return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, true, true));
        } else if (isInvisible) {
            return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, false, false));
        }
        return origin;
    }

    @Override
    public boolean isAlive() {
        return battleStatistics.getHealth() > 0;
    }

}
