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
