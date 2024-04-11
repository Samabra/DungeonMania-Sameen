package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
public class SceptreTest {

    @Test
    @Tag("4-1")
    @DisplayName ("Sceptre mind controls mercenary")
    public void sceptreMindControls() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreMindControlsMercenary", "c_sceptreMindControlsMercenary");

        // Ingredients to build sceptre in Map
        assertEquals(1, TestUtils.getEntities(res, "wood").size());
        assertEquals(1, TestUtils.getEntities(res, "key").size());
        assertEquals(1, TestUtils.getEntities(res, "sun stone").size());

        // pick up key 
        res = dmc.tick(Direction.RIGHT);
        // pick up sun stone
        res = dmc.tick(Direction.RIGHT);
        // pick up wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun stone").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Build sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Use sceptre to mind control mercenary
        List <EntityResponse> playerEntity = TestUtils.getEntities(res, "player");
        String id = playerEntity.get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(id));
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());

        // Mind control takes effect for two ticks
        // Starts in current tick, and continues for next tick
        res = dmc.tick(Direction.DOWN);

        // Overlapping positions of player and mercenary. No battle should be initiated
        Position playerPos = TestUtils.getEntities(res, "player").get(0).getPosition();
        Position mercenaryPos = TestUtils.getEntities(res, "mercenary").get(0).getPosition();

        // Ensure that player position and mercenary position is the same
        assertEquals(playerPos, mercenaryPos);

        // Mercenary should still be alive
        assertEquals(1, TestUtils.getEntities(res, "mercenary").size());

        // No battle should occur
        assertEquals(0, res.getBattles().size());

        // Mind control effect now wears off
        // Player cannot move down so they're forced to stay in the same spot
        res = dmc.tick(Direction.DOWN);

        // Mercenary and Player should now be in the same position
        // Battle will now start with Mercenary and Player
        // Mercenary should no longer be on the map
        List<EntityResponse> entities = res.getEntities();

        // There should now be an instance of a battle
        assertEquals(1, res.getBattles().size());
        assertTrue(TestUtils.countEntityOfType(entities, "mercenary") == 0);
    }

    @Test
    @Tag("4-2")
    @DisplayName("Sceptre cannot be built due to insufficient materials - no Sun Stone")
    public void SceptreInsufficientIngredients() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreMindControlsMercenary", "c_sceptreMindControlsMercenary");

        
    }
}
