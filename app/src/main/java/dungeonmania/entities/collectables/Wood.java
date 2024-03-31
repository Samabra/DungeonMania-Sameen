package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Wood extends Entity implements InventoryItem, Overlappable {
    public Wood(Position position) {
        super(position);
    }
}
