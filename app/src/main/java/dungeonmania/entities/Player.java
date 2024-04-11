package dungeonmania.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.Battleable;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Mercenary;
import dungeonmania.entities.enemies.ZombieToastSpawner;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.playerState.BaseState;
import dungeonmania.entities.playerState.PlayerState;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.exceptions.InvalidActionException;
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

    private PlayerState state;

    public Player(Position position, double health, double attack) {
        super(position);
        battleStatistics = new BattleStatistics(health, attack, 0, BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
                BattleStatistics.DEFAULT_PLAYER_DAMAGE_REDUCER);
        inventory = new Inventory();
        state = new BaseState(this);
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

    public boolean build(String buildable, EntityFactory factory, GameMap map) throws InvalidActionException {
        List<String> buildables = getBuildables();
        if (!buildables.contains(buildable)) {
            throw new InvalidActionException(String.format("%s cannot be built", buildable));
        }
        if (buildable.equals("midnight_armour")
            && map.getZombies()) {
            throw new InvalidActionException(String.format("%s cannot be built", buildable));
        }
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
        return enemy instanceof Mercenary && (((Mercenary) enemy).isAllied()
                || ((Mercenary) enemy).isMindControlled());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Game interact(String entityId, GameMap map, Game game)  throws IllegalArgumentException, InvalidActionException {
        Entity e = map.getEntity(entityId);
        if (e == null || !(e instanceof Interactable))
            throw new IllegalArgumentException("Entity cannot be interacted");
        if (e instanceof Mercenary) {
            if (inventory.itemExists(Sceptre.class)
                && !((Mercenary) e).isMindControlled()) {
                Sceptre sceptre = inventory.getFirst(Sceptre.class);
                ((Mercenary) e).mindControl(sceptre.getMindControlDuration());
            } else if (((Mercenary) e).isInteractable(this)
                        && !((Mercenary) e).isAllied()) {
                ((Mercenary) e).bribe(this);
            } else {
                throw new InvalidActionException("Entity cannot be interacted");
            }
        } if (e instanceof ZombieToastSpawner
                    && ((ZombieToastSpawner) e).isInteractable(this)) {
            weaponUse(game);
            map.destroyEntity(e);
        } else {
            throw new InvalidActionException("Entity cannot be interacted");
        }
        return game;
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
            state.transitionBase();
            return;
        }
        inEffective = queue.remove();
        if (inEffective instanceof InvincibilityPotion) {
            state.transitionInvincible();
        } else {
            state.transitionInvisible();
        }
        nextTrigger = currentTick + inEffective.getDuration();
    }

    public void changeState(PlayerState playerState) {
        state = playerState;
    }

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
        if (state.isInvincible()) {
            return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, true, true));
        } else if (state.isInvisible()) {
            return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, false, false));
        }
        return origin;
    }

    @Override
    public boolean isAlive() {
        return battleStatistics.getHealth() > 0;
    }

}
