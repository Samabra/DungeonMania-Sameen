package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Treasure extends Entity implements InventoryItem {
    public Treasure(Position position) {
        super(position);
    }
}
