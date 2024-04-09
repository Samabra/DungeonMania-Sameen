package dungeonmania.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class LogicEntitiesTest {

    // Light bulb tests
    @Test
    @Tag("5-1")
    @DisplayName("Test OR Light Bulb")
    public void orLightBulb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_orLightBulb", "c_logicEntitiesTest_orLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate switch
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on"));

    }

    @Test
    @Tag("5-2")
    @DisplayName("Test AND Light Bulb")
    public void andLightBulb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_andLightBulb", "c_logicEntitiesTest_andLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate switch
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate other switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on"));

    }

    @Test
    @Tag("5-3")
    @DisplayName("Test XOR Light Bulb (success)")
    public void xorLightBulbPass() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_xorLightBulb", "c_logicEntitiesTest_xorLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate switch to turn on light bulb
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on"));

        // activate other switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // lightbulb turns off
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));
    }

    @Test
    @Tag("5-11")
    @DisplayName("Test XOR Light Bulb (fail)")
    public void xorLightBulbFail() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_xorLightBulbFail", "c_logicEntitiesTest_coandLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate switch to turn on light bulb
        // does not turn on if 2 there are 2 cardinally adjacent switches
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));
    }

    @Test
    @Tag("5-4")
    @DisplayName("Test CO_AND Light Bulb (success)")
    public void coandLightBulbPass() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandLightBulb", "c_logicEntitiesTest_coandLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate switch to turn on light bulb
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_on"));
    }

    @Test
    @Tag("5-5")
    @DisplayName("Test CO_AND Light Bulb (fail)")
    public void coandLightBulbFail() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandLightBulb", "c_logicEntitiesTest_coandLightBulb");
        assertEquals(1, TestUtils.getEntities(res, "light_bulb_off"));

        // light bulb is not turned on
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate first switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

        // activate second switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // cannot turn on light bulb even if both switches are activated
        assertEquals(0, TestUtils.getEntities(res, "light_bulb_on"));

    }

    // bomb tests
    @Test
    @Tag("5-6")
    @DisplayName("Test OR Bomb")
    public void orBomb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_orBomb", "c_logicEntitiesTest_orBomb");
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate switch -> bomb detonated
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "bomb"));

    }

    @Test
    @Tag("5-7")
    @DisplayName("Test AND Bomb")
    public void andBomb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_andBomb", "c_logicEntitiesTest_andBomb");
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate switch
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate other switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // bomb detonates
        assertEquals(0, TestUtils.getEntities(res, "bomb"));

    }

    @Test
    @Tag("5-8")
    @DisplayName("Test XOR Bomb")
    public void xorBomb() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_xorBomb", "c_logicEntitiesTest_xorBomb");
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate switch to detonate bomb
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "bomb"));

    }

    @Test
    @Tag("5-9")
    @DisplayName("Test CO_AND Bomb (success)")
    public void coandBombPass() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandBomb", "c_logicEntitiesTest_coandBomb");
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate switch to detonate bomb
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.getEntities(res, "bomb"));
    }

    @Test
    @Tag("5-10")
    @DisplayName("Test CO_AND Light Bulb (fail)")
    public void coandBombFail() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandBomb", "c_logicEntitiesTest_coandBomb");
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate first switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

        // activate second switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // cannot detonate even if both switches are activated
        assertEquals(1, TestUtils.getEntities(res, "bomb"));

    }

    @Test
    @Tag("5-11")
    @DisplayName("Test OR switch door (PASS)")
    public void orSwitchDoorPass() {
        //  S   SD
        //  B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_orSwitchDoor", "c_logicEntitiesTest_orSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate switch and open door
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // enter through door successfully
        res = dmc.tick(Direction.UP);
        // position should not be the same as before
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-12")
    @DisplayName("Test OR switch door (FAIL)")
    public void orSwitchDoorFail() {
        //  S   SD
        //  B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_orSwitchDoor", "c_logicEntitiesTest_orSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate switch and open door
        res = dmc.tick(Direction.RIGHT);

        res = dmc.tick(Direction.UP);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // attempt to enter through door
        res = dmc.tick(Direction.UP);
        // position should be the same as before
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-13")
    @DisplayName("Test AND switch door (PASS)")
    public void andSwitchDoorPass() {
        //  S   SD  S
        //  B       B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_andSwitchDoor", "c_logicEntitiesTest_andSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate first switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // activate second switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.LEFT);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // enter through door
        res = dmc.tick(Direction.UP);
        // position should not be the same as before
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-14")
    @DisplayName("Test AND switch door (FAIL)")
    public void andSwitchDoorFail() {
        //  S   SD  S
        //  B       B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_andSwitchDoor", "c_logicEntitiesTest_andSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate first switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);

        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // attmept to go through door
        res = dmc.tick(Direction.UP);
        // position should be the same as before
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-15")
    @DisplayName("Test XOR switch door (PASS/FAIL)")
    public void xorSwitchDoor() {
        //  S   SD  S
        //  B       B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_xorSwitchDoor", "c_logicEntitiesTest_xorSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate first switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);

        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // attmept to go through door
        res = dmc.tick(Direction.UP);
        // position should not be the same as before
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        // walk down from door
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // activate second switch (should close the door)
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.LEFT);
        pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // attempt to go through door again
        res = dmc.tick(Direction.UP);

        // should be same position as before since door is now closed
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-16")
    @DisplayName("Test CO_AND switch door (PASS)")
    public void coandSwitchDoorPass() {
        // W is wire in this example, not wall

        //  W   W
        //  W   D
        //  W   S
        //      B
        //      P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandSwitchDoor", "c_logicEntitiesTest_coandSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate both switches in the same tick
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);

        // enter through door
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = dmc.tick(Direction.LEFT);
        // player should move through door, so position is not the same
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("5-17")
    @DisplayName("Test AND switch door (Fail)")
    public void coandSwitchDoorFail() {
        //  S   SD  S
        //  B       B
        //  P
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_logicEntitiesTest_coandSwitchDoorFail",
                "c_logicEntitiesTest_andSwitchDoor");
        assertEquals(1, TestUtils.getEntities(res, "switch_door"));

        // activate first switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        // activate second switch
        res = dmc.tick(Direction.UP);

        res = dmc.tick(Direction.LEFT);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        // attempt to enter through door
        res = dmc.tick(Direction.UP);
        // position should be the same as before
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

}
