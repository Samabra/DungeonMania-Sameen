// package dungeonmania.task2;

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

// public class BasicGoalsTest {
//     @Test
//     @Tag("1-1")
//     @DisplayName("Test achieving an enemy goal where there is only one spider")
//     public void oneSpider() {
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_basicGoalsTest_singleSpider", "c_basicGoalsTest_singleEnemy");

//         // goal not met
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         // attack spider
//         res = dmc.tick(Direction.RIGHT);
//         // goal met
//         assertEquals("", TestUtils.getGoals(res));
//     }

//     @Test
//     @Tag("1-2")
//     @DisplayName("Test achieving an enemy goal where there are multuple enemies")
//     // 1 spider, 1 mercenary, 1 zombieToast
//     public void threeSpiders() {
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_basicGoalsTest_threeEnemies", "c_basicGoalsTest_threeEnemies");

//         res = dmc.tick(Direction.RIGHT);
//         List<EntityResponse> entities = res.getEntities();
//         int spiderCount = TestUtils.countEntityOfType(entities, "spider");
//         int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         int mercCount = TestUtils.countEntityOfType(entities, "mercenary");
//         assertEquals(1, spiderCount);
//         assertEquals(0, zombieCount);
//         assertEquals(1, mercCount);

//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         res = dmc.tick(Direction.RIGHT);
//         entities = res.getEntities();
//         spiderCount = TestUtils.countEntityOfType(entities, "spider");
//         zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         mercCount = TestUtils.countEntityOfType(entities, "mercenary");
//         assertEquals(1, spiderCount);
//         assertEquals(0, zombieCount);
//         assertEquals(0, mercCount);

//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         res = dmc.tick(Direction.RIGHT);
//         res = dmc.tick(Direction.RIGHT);
//         entities = res.getEntities();
//         spiderCount = TestUtils.countEntityOfType(entities, "spider");
//         zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         mercCount = TestUtils.countEntityOfType(entities, "mercenary");
//         assertEquals(0, spiderCount);
//         assertEquals(0, zombieCount);
//         assertEquals(0, mercCount);
//         assertEquals(1, TestUtils.countEntityOfType(entities, "player"));

//         // goal should be achieved after killing 3 enemies
//         assertEquals("", TestUtils.getGoals(res));
//     }

//     @Test
//     @Tag("1-3")
//     @DisplayName("Test achieving an enemy goal when zombieToast is destroyed before spawner")
//     public void zombieToast1() {

//         // assert goal not achieved when zombie is destroyed but not spawner
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_basicGoalsTest_zombieBeforeSpawner",
//                 "c_basicGoalsTest_zombieBeforeSpawner");

//         // destroy zombie first
//         // move player to the right
//         res = dmc.tick(Direction.RIGHT);
//         List<EntityResponse> entities = res.getEntities();
//         int zombieToastSpawnerCount = TestUtils.countEntityOfType(entities, "zombie_toast_spawner");
//         int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(1, zombieToastSpawnerCount);
//         assertEquals(0, zombieCount);
//         // goal condition is not met
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         // destroy spawner
//         // move player to the right
//         res = dmc.tick(Direction.RIGHT);
//         entities = res.getEntities();
//         zombieToastSpawnerCount = TestUtils.countEntityOfType(entities, "zombie_toast_spawner");
//         zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(0, zombieToastSpawnerCount);
//         assertEquals(0, zombieCount);

//         // goal condition is met
//         assertEquals("", TestUtils.getGoals(res));

//     }

//     @Test
//     @Tag("1-4")
//     @DisplayName("Test achieving an enemy goal when spawner is destroyed before zombieToast")
//     public void zombieToast2() {
//         // assert goal not achieved when zombie is destroyed but not spawner
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_basicGoalsTest_spawnerBeforeZombie",
//                 "c_basicGoalsTest_spawnerBeforeZombie");

//         // destroy spawner first
//         // move player to the right
//         res = dmc.tick(Direction.RIGHT);
//         List<EntityResponse> entities = res.getEntities();
//         int zombieToastSpawnerCount = TestUtils.countEntityOfType(entities, "zombie_toast_spawner");
//         int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(0, zombieToastSpawnerCount);
//         assertEquals(1, zombieCount);
//         // goal condition is not met
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         // destroy zombie
//         // move player to the right
//         res = dmc.tick(Direction.RIGHT);
//         entities = res.getEntities();
//         zombieToastSpawnerCount = TestUtils.countEntityOfType(entities, "zombie_toast_spawner");
//         zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(0, zombieToastSpawnerCount);
//         assertEquals(0, zombieCount);

//         // goal condition is met
//         assertEquals("", TestUtils.getGoals(res));

//     }

// }
