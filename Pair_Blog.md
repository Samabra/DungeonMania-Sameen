# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/5)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

[Answer]
In both Mercenary.java and ZombieToast.java, there was a series of repetitive if statements
in the "move" method, in particular onwards from the check of the InvincibilityPotion.

> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

[Answer]
Currently, much of this logic is irrelevant and can be abstracted away. As such, 
we can use a Strategy pattern that delegrates this responsibility/logic into another
file to simplify things, thereby removing the repeated code. This design pattern
allows flexibility as we utilise composition by encapsulating an interface, so for whatever
reason we need to change how the enemy moves, it can be done so without modifying the actual
enemy files.

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

[Briefly explain what you did]
I created the EnemyMovement interface which only had an "apply" method which would
be responsible for changing the movement of the enemy when called. Next, MoveSpider,
MoveZombieToast, and MoveMercenary was created to contain the logic that we would 
like to abstract away from the offending files. 
Since we're using a new instance variable through composition, we're required to 
intialise this variable in the constructutor with the help of getters and setters. 
And also, any required getters and setters for this refactoring was made.


### b) Observer Pattern

https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/6


> Identify one place where the Observer Pattern is present in the codebase, and outline how the implementation relates to the key characteristics of the Observer Pattern.

[Answer]
The observer pattern is present in the public class Switch, which extends an abstract class called Entity. In this relationship, the bombs are the observers and the Switch is the subject. The Switch class contains
a private list of Bomb instances called "bombs", consistent with the characteristics of the Observer Pattern since the Subject contains a list of its observers. This is indicated by several other factors, which are all characteristics of an Observer Pattern.

In the Observer Pattern the Observer should be able to subscribe/unsubscribe from the Subject. Yet, a Switch cannot unsubscribe from a Bomb, which ascertains the fact that Switch is our Subject and Bomb is our observer even though both have a subscribe method. Additionally, Switch has an unsubscribe method  which allows for dynamic subscription/unsubscription of a Bomb from a Switch.

Furthermore, the Bomb cannot notify or update the Switch, as these methods do not exist in Switch. However, a notify method is implemented 
in Bomb, and Switch can call this method to notify a Bomb in a Switch's list of bombs when it is activated, in which a Bomb is notified, thereby changing the state of the Bomb (Bomb explodes).


The relationship is a many-to-many relationship, but has a one-to-many dependency on Switch. A Bomb can subscribe to many Switch's and a Switch can subscribe to many Bomb's. Nonetheless, a one-to-many dependency does exist, and that is on Switch.
Only Switch has the capacity to notify or update the state of many Bomb's. A Bomb cannot reciprocate this action. Hence there is 
still a one-to-many relationship, in the sense that there is a one-to-many dependency.

Thus the relationship between Switch and Bomb is indicative of an Observer Pattern in this code base.

### c) Inheritance Design

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/7)

> i. Name the code smell present in the above code. Identify all subclasses of Entity which have similar code smells that point towards the same root cause.

[Answer]
Since the onOverLap, onMovedAway and onDestroy methods are defined to be 
functionally redundant, it is a refused bequest! (violates Liskov Substitution Principle)
Subclasses with this code smell includes:
-   Boulder.java
-   Door.java
-   Exit.java
-   Player.java
-   Portal.java
-   Switch.java
-   Wall.java
-   Arrow.java
-   Bomb.java
-   Key.java
-   Sword.java
-   Treasure.java
-   Wood.java
-   ZombieToastSpawner.java
-   Builder.java (abstract superclass of Bow and Shield)
-   Potion.java (abstract superclass of InvincibilityPotion and InvisibilityPotion)
-   Enemy.java (abstract superclass of Mercenary, Spider, ZombieToast)


> ii. Redesign the inheritance structure to solve the problem, in doing so remove the smells.

[Briefly explain what you did]
Since:
Only Enemy and ZombieToastSpawner implemented onDestroy.
Only Switch implemented OnMovedAway.
Only Boulder, Door, Player, Portal, Switch, Arrow, Bomb, Key, Sword, Treasure, Wood, 
Potion and Enemy implemented OnOverlap.
I decided that it would be better off to create separate interfaces as a marker 
that these methods exist in the entity object. Alternatively, the classic approach
would be to delegate these methods elsewhere, but this would ruin the ability to 
store the entities all in one list or cause other type incompatibilities. Anyhow, 
I removed all of the redundant methods and allowed the classes that implemented the
methods to implement the respective interfaces. Then to also fix type incompabilities
when looping through the entire entity list in GameMap, I had to typecast to the 
correct interface.
Also additionally changed the hardcoded return canMoveOnto method in Entity.java 
to true and removed all instances of the overrides that hardcoded returned true in
its subclasses and added hardcoded returns to false to respective files.

### d) More Code Smells

[Links to your merge requests]
https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/9/diffs


> i. What design smell is present in the above description?
The design smell present in the code is the issue of high coupling. The description indicates a violation of the Single Responsibility Principle, as well as an exhibition of the Shotgun Surgery code smell. Modifying the Player class to implement picking up collectable entities, only to then require modifications to many other parts of the codebase is indicative of high coupling issues, since modifying Player to accommodate for picking up collectable entities resulted in having to change other parts of the code, exposing the dependency of the other classes on Player. 

Moreover, this description demonstrates a clear violation of the Single Responsbility Principle. The Single Responsbility Principle states that a class should only have one reason to change (that is a class should only have a single responsibility). The changes required in the description illustrates a lack of separation of concerns, as the responsbility of picking up collectable items is shared between Player and collectable entities, which leads to collectable entities having more than one
responsibility as not only it defines the collectable item with its attributes, it also handles how it is picked up. Picking up items is an interaction of a Player, so it is within the bounds of the Player class' responsibility, hence should be managed
by the Player class alone. 

Finally, the description exhibits the Shotgun Surgery code smell, as many changes are required in many different classes to change one single action.

As demonstrated above, the design smell is the issue of high coupling, which indicates a violation of the Single Responsbility Principle and exhibits a Shotgun Surgery code smell. 

[Answer]

> ii. Refactor the code to resolve the smell and underlying problem causing it.

[Briefly explain what you did]

The refactored code now implements pickUp from the perspective of a Player when it overlaps with a collectable entity, not from the perspective of a colletable entity that has been overlapped or stepped on. This meant that;
- All overriding onOverlap methods in all collectable entities were removed, as they handled the responsibility of being picked up as well, by calling a method in Player, introducing tight coupling.
- Refactored the code by reproducing the implementation of all the redundant overriding onOverlap methods into overriding onOverlap method in Player. When Player steps on collectable entity, then entity is picked up.
- pickUp method in Player changed its paramater to accepting only instances of InventoryItem, as entity is checked to be an instance of InventoryItem in Player onOverlap method.
- If collectable entity is an instance of a bomb, the bomb was checked if it is spawned. If that is true, then bomb is picked up by Player, Bomb entity is removed from map and bomb is set to state INVENTORY
- For all other collectable entities, they were picked up by Player and removed from map as usual.
- In GameMap.java, the triggerOnOverlap method was modified to trigger the Player picking up the collectable entity. This was done by keeping original implementation, but adding another condition which checks if the entity is a Player
### e) Open-Closed Goals

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/8)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?
Looking at GoalFactory.java first, this is a factory method, so the usage of switch
statements are unavoidable (there is nothing wrong with it!). However, looking 
at Goal.java, both the "achieved" and "toString" methods uses complex, large switch statements 
which is a code smell. This does not comply with the open-closed principle since
it is neither open for extension nor closed for modification, this makes it vulnerable
to divergent change since all if and switch statements would need to be modified if a new
feature is added. In addition, there are conditions where the variables "target", goal1
and goal2 are unused which is partially dead code. This definitely would ask for a 
change in design as this current design makes it difficult to maintain and extend. 

[Answer]

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

[Briefly explain what you did]
By observation,  the structure of Goal also stores two children Goals indicates  
the presence of a tree structure. We should be utilising the Composite Pattern in 
this case to remove the switch statements!
I changed the original Goal class to an interface that will be implemented by 
abstract class CompoundGoal and other leaf nodes since this is a feature of the 
Composite Pattern where leaf and compound nodes are not discriminated. 
P.S I decided not to make an abstract class for leaf nodes, as it was pertained with limited
functionality. 
Then I created the respective concrete classes to inherit these abstract classes 
or implement Goal as required.
Compound Nodes:
-   AndGoal
-   OrGoal
Leaf Nodes:
-   ExitGoal
-   BouldersGoal
-   TreasureGoal
This also forced me to alter the constructors that are used in GoalFactory.

### f) Open Refactoring

https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/10


- Revisited onOverlap method in Player which was originally changed to handle pickup of collectable entities.
- onOverlap in Player violates the Law of Demeter, where the Game object is returned by the getGame method and has its
"battle" method invoked.
The class is only accessible through class Map, to which Player does have access to.
- Nested sequence of if statement in Player in method onOverlap. 
- To simplify the onOverlap method further, I extracted methods out of the onOverlap method. If entity paramater is instance of an Enemy, I simply check if the enemy is a mercenary and is allied in a private boolean method in Player, called mercenaryIsAllied, thereby reducing the if statement nesting. In addressing the violation of the Law of Demeter, I implemented a method in Map, called initiateBattle which basically runs the battle method for component Game class in Map. So Player calls the initiateBattle method in Map, and Map calls battle in Game, thus removing the violation altogether. 

https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/11

- Having a look at class Game, the method battle also violates the Law of Demeter as it calls a method in BattleStatistics, called getHealth(). BattleStatistics is not a component class of class Game. 
- To address this Law of Demeter issue, a public boolean method called isAlive() was implemented in Battleable Interface, as this method is a feature of both Enemy and Player class, and is only a concern if the entity is Battleable. The method has the same implementation in Enemy and Player. The method in question calls the getHealth method of the local battleStatistics component class located within either Enemy or Player, and compares it with the value of 0. The method itself is just one line, checking to see if getHealth() returns an integer greater than 0 or not.
- Since the code is the exact same for both Enemy and Player, an inheritance approach was considered, where it could have been placed within Entity instead, but Entity does not have access to a BattleStatistics class, nor does it make sense for an Entity in general to have health, as most entities were not capable of life.


https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/12

- onOverlap method in Enemy violated the Law of Demeter. Same issue as in Player, where the method was trying to call a method in class Game, but Enemy does not keep Game as a component.
- Used the already set method of initiateBattle in Map to be called by onOverlap in Enemy.

https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/18
-   Removed all deprecated methods as required, since it may break backwards compatibility.
    Also, they are compiler generator error messages that are not good style to keep in


Add all other changes you made in the same format here:

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/13
)

**Assumptions**

[Any assumptions made]
-   Assumed that there were no zombie_toast_spawners when enemy_goal == 0. 
-   Assumed that enemy_goal is NOT achieved when enemy_goal == 0 and there is 
    no active player.

**Design**

[Design]
Stuck with the original  design from the MVP, where EnemyGoal implemented the Goal interface.

**Changes after review**

[Design review/Changes made]
-   Introduced a few wrapper methods to get spawn count and enemy kill count
    so that Law of Demeter is not violated
-   Implemented EnemiesGoal methods as required by its Goal interface, which had
    "toString" and "achieved" methods
-   "toString" method return "" if achieved, otherwise ":enemies" was returned
-   "achieved" method returned true if both spawn count is zero and the zombieToast
    target count was met.
No changes to design! (tentative)

**Test list**

[Test List]
For Basic Goals:
-   Test achieving an enemy goal where there is only one spider
-   Test achieving an enemy goal where there are multuple enemies
-   Test achieving an enemy goal when zombieToast is destroyed before spawner
-   Test achieving an enemy goal when spawner is destroyed before zombieToast

For complex Goals:
-   Testing a map with 4 conjunction goal
-   Testing enemies and exit goal
-   Test achieving enemies or exit goal

**Other notes**

[Any other notes]

### Choice 1 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 2 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 3 (Insert choice) (If you have a 3rd member)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

## Task 3) Investigation Task ⁉️

[Merge Request 1](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T1/teams/M11B_JUKEBOX/assignment-ii/-/merge_requests/13)

[Briefly explain what you did]
Looking at ZombieTest.java in the toastDestruction test, we can see 
that the zombieToastSpawner is adjacent to the player and there is an attempt
of player-spawner interaction. However, the the spawner is stll present
after the interation, when it should be destroyed. This obviously isn't 
expected behaviour, so we can deduce that this is a bug. 
Steps taken to fix this:
-   Remove the instance of the zombieToastSpawner when "interact" is invoked.
-   Modify the buggy test to assert that the zombieToastSpawner count was zero
    after player interaction.

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:
