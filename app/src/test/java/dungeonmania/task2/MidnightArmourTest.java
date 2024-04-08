package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.RoundResponse;
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
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());

        // Build midnight Armour
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
    }

    @Test
    @Tag("4-2")
    @DisplayName("Test achieving midnight Armour creation when zombies are present")
    public void buildArmourZombiesPresent() {
        //              W   W   W
        //  P   S   S   D   Z   W
        //              W   W   W
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_buildArmourWithZombie",
                "c_midnightArmourTest_buildArmourWithZombie");

        // obtain sunStone, sword
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());

        // can't build midnight armour
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));

        // walk through door and kill zombie
        List<EntityResponse> entities = res.getEntities();
        int zombieCount = TestUtils.countEntityOfType(entities, "zombie");
        assertEquals(1, zombieCount);

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        entities = res.getEntities();
        zombieCount = TestUtils.countEntityOfType(entities, "zombie");
        assertEquals(0, zombieCount);

        // should successfully build midnight armour
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

    }

    @Test
    @Tag("4-3")
    @DisplayName("Test midnight armour attack and defence bonuses")
    public void midnightArmourStats() {
        DungeonManiaController dmc = new DungeonManiaController();
        String config = "c_midnightArmourTest_armourStats";
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_armourStats", config);

        // obtain sunStone, sword
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());

        // open door with sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // build armour
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());

        // start the battle
        res = dmc.tick(Direction.RIGHT);
        BattleResponse battle = res.getBattles().get(0);
        RoundResponse firstRound = battle.getRounds().get(0);

        // Assumption: Armour effect calculation to reduce damage makes enemyAttack =
        // enemyAttack - shield effect
        int enemyAttack = Integer.parseInt(TestUtils.getValueFromConfigFile("spider_attack", config));
        int armourDefenceEffect = Integer.parseInt(TestUtils.getValueFromConfigFile("midnight_armour_defence", config));
        int playerExpectedDamage = (enemyAttack - armourDefenceEffect) / 10;
        // Delta health is negative so take negative here
        assertEquals(playerExpectedDamage, -firstRound.getDeltaCharacterHealth(), 0.001);

        int armourAttackEffect = Integer.parseInt(TestUtils.getValueFromConfigFile("midnight_armour_attack", config));
        int playerAttack = Integer.parseInt(TestUtils.getValueFromConfigFile("player_attack", config));
        int enemyExpectedDamage = (playerAttack + armourAttackEffect) / 5;
        assertEquals(enemyExpectedDamage, -firstRound.getDeltaEnemyHealth(), 0.001);

    }
}
