package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Treasure extends Entity implements InventoryItem, Overlappable {
    public Treasure(Position position) {
        super(position);
    }
}
