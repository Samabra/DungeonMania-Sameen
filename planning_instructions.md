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
a)
b)
c)
d)
e)
f)

## Task 2

**Microevolution**
Test edge cases:
**SunStone & More buildables**
Test edge cases:

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

## Task 3