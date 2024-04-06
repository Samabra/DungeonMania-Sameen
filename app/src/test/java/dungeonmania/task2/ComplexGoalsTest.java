// package dungeonmania.task2;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.List;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Tag;
// import org.junit.jupiter.api.Test;

// import dungeonmania.DungeonManiaController;
// import dungeonmania.mvp.TestUtils;
// import dungeonmania.response.models.DungeonResponse;
// import dungeonmania.response.models.EntityResponse;
// import dungeonmania.util.Direction;

// public class ComplexGoalsTest {
//     @Test
//     @Tag("2-1")
//     @DisplayName("Testing a map with 4 conjunction goal")
//     public void andAll() {
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_complexGoalsTest_andAllTask2", "c_complexGoalsTest_andAllTask2");

//         List<EntityResponse> entities = res.getEntities();
//         int spiderCount = TestUtils.countEntityOfType(entities, "spider");
//         assertEquals(1, spiderCount);
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));
//         assertTrue(TestUtils.getGoals(res).contains(":treasure"));
//         assertTrue(TestUtils.getGoals(res).contains(":boulders"));

//         // kill spider
//         res = dmc.tick(Direction.RIGHT);
//         spiderCount = TestUtils.countEntityOfType(entities, "spider");
//         assertEquals(0, spiderCount);
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));
//         assertTrue(TestUtils.getGoals(res).contains(":treasure"));
//         assertTrue(TestUtils.getGoals(res).contains(":boulders"));

//         // move boulder onto switch
//         res = dmc.tick(Direction.RIGHT);
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));
//         assertTrue(TestUtils.getGoals(res).contains(":treasure"));
//         assertFalse(TestUtils.getGoals(res).contains(":boulders"));

//         // pickup treasure
//         res = dmc.tick(Direction.DOWN);
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));
//         assertFalse(TestUtils.getGoals(res).contains(":treasure"));
//         assertFalse(TestUtils.getGoals(res).contains(":boulders"));

//         // move to exit
//         res = dmc.tick(Direction.DOWN);
//         assertEquals("", TestUtils.getGoals(res));
//     }

//     @Test
//     @Tag("2-2")
//     @DisplayName("Testing enemies and exit goal")
//     // player kills zombie first, then passes by exit to kill spawner on the other side
//     // player goes back for exit
//     public void enemyAndExit() {
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_complexGoalsTest_enemiesAndExitTask2",
//                 "c_complexGoalsTest_enemiesAndExitTask2");

//         List<EntityResponse> entities = res.getEntities();
//         int zombieToastCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(1, zombieToastCount);
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));

//         // kill zombieToast
//         res = dmc.tick(Direction.RIGHT);
//         zombieToastCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(0, zombieToastCount);
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         // move right to step on exit
//         res = dmc.tick(Direction.RIGHT);
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));

//         // move right to step out of exit and kill spawner
//         res = dmc.tick(Direction.RIGHT);
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));

//         // step back on exit
//         res = dmc.tick(Direction.LEFT);
//         assertEquals("", TestUtils.getGoals(res));
//     }

//     @Test
//     @Tag("2-3")
//     @DisplayName("Test achieving enemies or exit goal")
//     // enemies goal is achieved first
//     public void enemiesOrExit() {
//         DungeonManiaController dmc = new DungeonManiaController();
//         DungeonResponse res = dmc.newGame("d_complexGoalsTest_enemiesAndExitTask2",
//                 "c_complexGoalsTest_enemiesAndExitTask2");

//         List<EntityResponse> entities = res.getEntities();
//         int zombieToastCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(1, zombieToastCount);
//         assertTrue(TestUtils.getGoals(res).contains(":enemies"));
//         assertTrue(TestUtils.getGoals(res).contains(":exit"));

//         // kill zombieToast
//         res = dmc.tick(Direction.RIGHT);
//         zombieToastCount = TestUtils.countEntityOfType(entities, "zombie_toast");
//         assertEquals(0, zombieToastCount);

//         assertEquals("", TestUtils.getGoals(res));
//     }
// }
