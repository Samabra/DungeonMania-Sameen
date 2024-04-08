package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTest {
    @Test
    @Tag("4-1")
    @DisplayName("Test achieving a successful Armour build")
    public void buildArmour() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_buildArmour", "c_midnightArmourTest_buildArmour");

        // pick up sun stone and sword
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunStone").size());
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        assertEquals(0, TestUtils.getInventory(res, "midnightArmour").size());

        // Build midnight Armour
        res = assertDoesNotThrow(() -> dmc.build("midnightArmour"));
        assertEquals(1, TestUtils.getInventory(res, "midnightArmour").size());
        assertEquals(0, TestUtils.getInventory(res, "sunStone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
    }

    @Test
    @Tag("4-2")
    @DisplayName("Test can't create midnight Armour if zombies are present")
    public void buildArmourZombiesPresent() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_buildArmourWithZombie",
                "c_midnightArmourTest_buildArmourWithZombie");

    }
}
