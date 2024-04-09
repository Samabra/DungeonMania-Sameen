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
**Logic Switches**
Test edge cases:

Light Bulb
-   OR
-   AND
-   XOR
-   CO_AND

switch door
-   OR
-   AND
-   XOR
-   CO_AND

Bomb
-    OR
-   AND
-   XOR
-   CO_AND

## Task 3