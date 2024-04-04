package dungeonmania.task2;

// import dungeonmania.DungeonManiaController;
// import dungeonmania.mvp.TestUtils;
// import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.EntityResponse;
// import dungeonmania.util.Direction;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.List;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Tag;
// import org.junit.jupiter.api.Test;

public class BasicGoalsTest {
    // @Test
    // @Tag("1-1")
    // @DisplayName("Test achieving an enemy goal where there is only one spider")
    // public void oneSpider() {
    // DungeonManiaController dmc = new DungeonManiaController();
    // DungeonResponse res = dmc.newGame("d_basicGoalsTest_singleSpider",
    // "c_basicGoalsTest_singleEnemy");

    // // goal not met
    // assertTrue(TestUtils.getGoals(res).contains(":enemies"));

    // // attack spider
    //     res = dmc.tick(Direction.RIGHT);
    //     // goal met
    //     assertEquals("", TestUtils.getGoals(res));
    // }

    // @Test
    // @Tag("1-2")
    // @DisplayName("Test achieving an enemy goal where there is 1 of each spider, mercenary, zombie")
    // public void threeSpiders() {
    //     DungeonManiaController dmc = new DungeonManiaController();
    //     DungeonResponse res = dmc.newGame("d_basicGoalsTest_threeEnemies", "c_basicGoalsTest_threeEnemies");

    //     DungeonResponse postBattleResponse = dmc.tick(Direction.RIGHT);
    //     List<EntityResponse> entities = postBattleResponse.getEntities();
    //     int spiderCount = TestUtils.countEntityOfType(entities, "spider");
    //     int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
    //     int mercCount = TestUtils.countEntityOfType(entities, "mercenary");
    //     assertEquals(1, spiderCount);
    //     assertEquals(0, zombieCount);
    //     assertEquals(1, mercCount);

    //     assertTrue(TestUtils.getGoals(res).contains(":enemies"));

    //     postBattleResponse = dmc.tick(Direction.RIGHT);
    //     entities = postBattleResponse.getEntities();
    //     spiderCount = TestUtils.countEntityOfType(entities, "spider");
    //     zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
    //     mercCount = TestUtils.countEntityOfType(entities, "mercenary");
    //     assertEquals(1, spiderCount);
    //     assertEquals(0, zombieCount);
    //     assertEquals(0, mercCount);

    //     assertTrue(TestUtils.getGoals(res).contains(":enemies"));

    //     postBattleResponse = dmc.tick(Direction.RIGHT);
    //     postBattleResponse = dmc.tick(Direction.RIGHT);
    //     entities = postBattleResponse.getEntities();
    //     spiderCount = TestUtils.countEntityOfType(entities, "spider");
    //     zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
    //     mercCount = TestUtils.countEntityOfType(entities, "mercenary");
    //     assertEquals(0, spiderCount);
    //     assertEquals(0, zombieCount);
    //     assertEquals(0, mercCount);
    //     assertEquals(1, TestUtils.countEntityOfType(entities, "player"));

    //     // goal should be achieved after killing 3 enemies
    //     assertEquals("", TestUtils.getGoals(res));
    // }

    // @Test
    // @Tag("1-3")
    // @DisplayName("Test achieving an enemy goal when zombieToast is destroyed before spawner")
    // public void zombieToast1() {

    //     // assert goal not achieved when zombie is destroyed but not spawner

    // }

    // @Test
    // @Tag("1-4")
    // @DisplayName("Test achieving an enemy goal when spawner is destroyed before zombieToast")
    // public void zombieToast2() {

    // }

}
