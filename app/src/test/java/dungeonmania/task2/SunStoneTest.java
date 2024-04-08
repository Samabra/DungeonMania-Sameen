package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SunStoneTest {
    @Test
    @Tag("3-1")
    @DisplayName("Test sun stone can be picked up and counts to treasure goal")
    public void pickUpSunStone() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_pickUpSunStone", "c_sunStoneTest_pickUpSunStone");

        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        // move player to the right
        // collects sun stone
        res = dmc.tick(Direction.RIGHT);
        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
        // assert sun stone is collected
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("3-2")
    @DisplayName("Test sun stone can be used as a key")
    public void sunStoneAsKey() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_sunStoneAsKey", "c_sunStoneTest_sunStoneAsKey");

        // TODO: need to ask if the type can be "sun_stone"?
        assertEquals(1, TestUtils.getEntities(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getEntities(res, "sun stone").size());

        // pick up sun stone
        res = dmc.tick(Direction.RIGHT);

        // go through door
        res = dmc.tick(Direction.RIGHT);
        Position playerPos = TestUtils.getEntities(res, "player").get(0).getPosition();
        Position doorPos = TestUtils.getEntities(res, "door").get(0).getPosition();
        // assert player position should be equal to door position
        assertEquals(playerPos, doorPos);
        // assert that sun stone stays in inventory
        assertEquals(1, TestUtils.getInventory(res, "key").size());
    }

    @Test
    @Tag("3-3")
    @DisplayName("Test sun stone is consumed when used to craft/build")
    public void sunStoneAsBuildingMaterial() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_sunStoneAsBuildingMaterial",
                "c_sunStoneTest_sunStoneAsBuildingMaterial");

        assertEquals(1, TestUtils.getEntities(res, "sun_stone").size());
        assertEquals(1, TestUtils.getEntities(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());

        // pick up sun stone
        res = dmc.tick(Direction.RIGHT);
        // pick up treasure
        res = dmc.tick(Direction.RIGHT);
        // pick up wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunStone").size());
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Build sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // sun stone is consumed
        assertEquals(0, TestUtils.getInventory(res, "sunStone").size());
        // FIXME: double check since the spec say that material is retained after use when building entities
        // if the sunstone is treated as a treasure
        // is it implying that when building a sceptre for instance, if there is
    }

    @Test
    @Tag("3-4")
    @DisplayName("Test sun stone is not used if there is other preferred materials")
    public void sunStoneAsBackUpMaterial() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_sunStoneAsBackUpMaterial",
                "c_sunStoneTest_sunStoneAsBackUpMaterial");

        // pick up 2 x sun stone, wood and treasure
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sunStone").size());
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());

        // Build sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

    }

    @Test
    @Tag("3-5")
    @DisplayName("Test sun stone is used as a bribe")
    public void sunStoneAsBribe() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_sunStoneBribe", "c_sunStoneTest_sunStoneAsBribe");

        // FIXME: do this later
    }

}
