package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Arrow extends Entity implements InventoryItem {
    public Arrow(Position position) {
        super(position);
    }
}
