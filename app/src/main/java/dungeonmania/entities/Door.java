package dungeonmania.entities;

import dungeonmania.map.GameMap;

import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.util.Position;
import dungeonmania.entities.collectables.SunStone;


public class Door extends Entity implements Overlappable {
    private boolean open = false;
    private int number;

    public Door(Position position, int number) {
        super(position.asLayer(Entity.DOOR_LAYER));
        this.number = number;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (open || entity instanceof Spider) {
            return true;
        }

        return (entity instanceof Player && (hasKey((Player) entity) || hasSunStone((Player) entity)));
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (!(entity instanceof Player))
            return;

        Player player = (Player) entity;
        if (hasKey(player)) {
            Inventory inventory = player.getInventory();
            Key key = inventory.getFirst(Key.class);
            inventory.remove(key);
            open();
        }
        if (hasSunStone(player)) {
            open();
        }
    }

    private boolean hasKey(Player player) {
        Inventory inventory = player.getInventory();
        Key key = inventory.getFirst(Key.class);

        return (key != null && key.getnumber() == number);
    }

    private boolean hasSunStone(Player player) {
        Inventory inventory = player.getInventory();
        SunStone stone = inventory.getFirst(SunStone.class);
        return stone != null;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    public int getNumber() {
        return this.number;
    }


}
