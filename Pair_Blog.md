# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](/put/links/here)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

[Answer]

> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

[Answer]

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

[Briefly explain what you did]

### b) Observer Pattern



> Identify one place where the Observer Pattern is present in the codebase, and outline how the implementation relates to the key characteristics of the Observer Pattern.

[Answer]
The observer pattern is present in the public class Switch, which extends an abstract class called Entity. The class contains
a private list of Bomb instances called "bombs", in consistency with the characteristics of the Observer Pattern. The bombs, are the observers, whereas the Switch is an observer, indicated by a Bomb being able to subscribe or unsubcribe from a Switch, which 
fufills another characteristic about Observer Patterns. 

The Bomb class has a method called "notify", which basically explodes the Bomb, ceasing it to exist. In an overriding method called "onOverlap", inherited from the class Entity, if the bomb overlaps
with an instance of a Boulder, a boolean called activated is set to true, and the Bomb's notify method is called (which makes it explode). 

If a Bomb subscribes, and is activated, the Bomb is also notified (explodes). This fulfills another key trait of Observer Patterns, where each change of state in a subject (Switch in this example) notfies the observer of the change (A Bomb in this example), which causes the observer to update their state.

Thus the relationship between Switch and Bomb is a perfect example of an Observer Pattern in this code base.

### c) Inheritance Design

[Links to your merge requests](/put/links/here)

> i. Name the code smell present in the above code. Identify all subclasses of Entity which have similar code smells that point towards the same root cause.

[Answer]

> ii. Redesign the inheritance structure to solve the problem, in doing so remove the smells.

[Briefly explain what you did]

### d) More Code Smells

[Links to your merge requests](/put/links/here)

> i. What design smell is present in the above description?

[Answer]

> ii. Refactor the code to resolve the smell and underlying problem causing it.

[Briefly explain what you did]

### e) Open-Closed Goals

[Links to your merge requests](/put/links/here)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?

[Answer]

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

[Briefly explain what you did]

### f) Open Refactoring

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

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

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:
