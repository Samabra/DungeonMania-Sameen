## Misc

**Branch**
-   There should be a new branch created for every sub part i.e. a branch for 1a)
    and another branch for 1b) etc. Need nice branch names pls
- Do NOT delete branches when merging (Untick the box)

**Merge Requests**
-   For the convenience of code checking/reviewing, merge requests should contain code
    for only one subpart i.e. only 1a) or only 1b). It shouldn't contain both. (unless specified on spec)
-   Code will only be merged to master after it is reviewed by another person
-   Don't forget to include merge requests link to the appropriate section in your blog!
    (Both pair and individual blog) + follow any additional Instructions on the spec

Also as a side note: always constantly git pull origin  master to be on top of the changes.

**Code Reviewing**
-   Reviewing code consists of checking line by line, to make sure there is no bugs
    or any numerical/typo error if there is. The tests MUST be checked so that it covers
    every edge case. All of the exhaustive edge cases should be documented here in this
    file if possible. i.e. a brief dot point describing what the test emulates

## Task 1


## Task 2
Original MVP Branch Coverage: 83.6%
Original MVP Line coverage: 91.8%

**Microevolution**

Test edge cases:
Note: json goal is called "enemies" (must use in goalFactory)
Basic goals
-   1)   Goal reached with defeating only ZombieToast and destroys ZombieToast Spawner (done)
-   2)  Goal reached defeating one spider (Done)
-   3)  Goal reached with defeating only multiple Enemies (done)
-   5)  Goal NOT reached when destroying enough ZombieToast without the spawner (done)
-   6)  Goal NOT reached when destroyed spawner but not ZombieToast (done)
-   7)  Goal NOT reached when not enough Zombies are destroyed (zombie toast destroyed) (done)
-   8)  Goal NOT reached when not enough spiders are destroyed (Done)
Might need to test when goal is not reached since player died
Also need to test bribing the mercenary, then continue killing other enemies until 
enemy_count is reached
Test player health if there is time
Test swords/weapons to destroy enemies as well

Complex goals
-   1) AND all (done)
-   AND enemies (zombie and spawner), exit (done)
-   1) OR EXIT (1) first) (done)

Repeat the complex goals for 2), 3), 4), 5), 6) (if we're bored)

Stats After completing 2a)
    - Branch Coverage: 83.6%
    - Line Coverage: 92%
**SunStone & More buildables**
Test edge cases:

Test for sun stone :
-   Test sun stone can be successfully picked up by player (done)
-   Test stone counts towards treasure goal (done)
-   Test sun stone can be used for both as a treasure and key (done)
-   Use stone to open doors, must remain in inventory after
    Same scenario for when using the stone to replace material
-   Test use other treasures first if there is enough of those, then use sun stone if not enough
-   Test when used in crafting the stone is consumed (done)
-   Test cannot be used to bribe mercenaries or assassins (done)


Tests for public DungeonResponse build (String build) throw InvalidActionException
- Test: invalid 'buildable' parameter; If not bow, shield, sceptre or midnight armour -> throw IllegalArgumentException
- Test: insufficient items to build buildable entity -> throw InvalidActionException
- Test: Shield made with 2 wood + 1 Sun Stone when no treasure or key; Sun Stone still retained in inventory -> Pass
- Test: Shield made with 2 wood + 1 Treasure or Key when 1 or more treasure or key exists -> Pass
- Test: Sceptre crafted with 1 wood or 2 arrows + 1 key or treasure + 1 Sun Stone (when 1 or more treasure or key); 1 Sun Stone removed from inventory -> Pass
- Test: Sceptre crafted with 1 wood or 2 arrows + 1 Sun Stone (replacement) + 1 Sun Stone when no treasure or key; Only 1 Sun Stone removed from inventory -> Pass
- Test: Midnight Armour has sufficient items to be built but zombies in dungeon -> throw InvalidActionException
- Test: Midnight Armour has sufficient items to be built and no zombies in dungeon; 1 Sun Stone removed from inventory -> Pass
- Test: Midnight Armour is being built with 1 Sword + 1 Treasure or Key -> throw InvalidActionException

Tests for public DungeonResponse interact (String entityId) throws IllegalArgumentException
Test: invalid entityId -> throw IllegalArgumentException
Test: Player not within bribing radius of mercenary -> throw InvalidActionException 
Test: Player does not have enough gold to bribe mercenary -> throw InvalidActionException
Test: Player does not have sceptre to mind control mercenary -> throw InvalidActionException
Test: Player has sceptre and enough gold; Mind control mercenary and remove sceptre from inventory as sceptre becomes obsolete -> Pass
Test Player has sceptre and not enough gold; Mind control mercenary and remove sceptre from inventory -> Pass
Test: Player has enough gold and no sceptre; Bribe mercenary -> Pass
Test: If mercenary already mind controlled, mercenary cannot be bribed or mind controlled by a new sceptre -> throw InvalidActionException
Test: If mercenary is bribed, but Player builds sceptre, mercenary is now mind controlled and gold is returned to Player -> Pass
Test: If mercenary is bribed, Player can no longer bribe mercenary -> throw InvalidActionException
Test: After mind control period, Player can only bribe the specific mercenary or use a new Sceptre for mind control -> Pass
Test: Zombie Spawner not cardinally adjacent to Player -> throw InvalidActionException
Test: Player does not possess a weapon when attacking Zombie Spawner -> throw InvalidActionException
Test: Player cardinally adjacent to Zombie Spawner and Player has a weapon; Spawner destroyed -> Pass
**Logic Switches**
Test edge cases:

Light Bulb
-   OR (done)
-   AND (done)
-   XOR (done)
-   CO_AND (done)

switch door
-   OR (done)
-   AND (done)
-   XOR (done)
-   CO_AND (done)

Bomb
-    OR (done)
-   AND (done)
-   XOR (done)
-   CO_AND (done)

- not activated switch not conductive (done)
- logic bomb not conductive (done)
- switch door not conductive (done)
- light bulb not conductive (done)

// TODO: need to test whether logic bomb can be picked up
## Task 3