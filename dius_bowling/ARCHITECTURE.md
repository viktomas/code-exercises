Architecture of DiUS Bowling Scoring System
===========================================
This document serves as an explanation of architectonic decisions made during the process of implementing this assignment.

First of all, sorry. I validated my inputs with commons-lang3 library, breaking straight away 2 spec requirements. I just wasn't able to let them invalidated.

Core
----
Central access point to the app is `BowlingGame` class which has a public interface according to specification. It uses factories to create Frames. After creating frame it iterates through the whole list of all created Frames and adds current frame as a successor.

Factories
---------
Factories are stateful objects, which hold information about knocked pins and creates Frames when possible.

Factory has to distinguish between 3 cases:

* 10 pins knocked out in one bowl - creates Strike frame
* 10 pins knocked out in two bowls - creates Spare frame
* less than 10 pins knocked out in two bowls - creates OpenFrame

Standard usage of the factory is:

1. call `roll()`
2. check `isReady()` to know if we can create new Frame
3. `createFrame()` if it is possible

### FinishFrameFactory
Special case of factory. The Game uses it instead of SimpleFrameFactory after 10nth frame.

Frames
------
Classes with a unified interface for holding state of one frame. It is possible to add successors to them via `#addSuccessor()` method. Every implementation process this method differently, but unified approach is this: If I don't need, I simply ignore it. This approach lets me get rid of all special cases with different Frames needing a different number of successors.

For the same reason method `#getRolls ()` returns list of rolls and for example Strike process it like this: I concatenate rolls from all my successors, take first two of them and add them to my score.

What I'm happy with
-------------------
I'm happy with my approach to making the complexity of the system as small as possible. (By lowering complexity I basically mean reducing decisions in app).

This can be seen in several cases:

* EmptyFrame used in FinishFrameFactory and Spare
* Way of letting Frames know about their successors and computing successor's score
* Switching Factory at the end of the game so the rest of the code can stay untouched.

What I'm not happy with
-----------------------
With last commit(adding solution for the end of the game), I raised significant complexity of my app. Especially FinishFrameFactory shouldn't inherit from SimpleFrameFactory, because FFF is not a SFF, but I was already using more time than I wanted to.

Used time, I wanted to use just 2 hours, but Given the fact that this is my only shot for working with you, I couldn't submit half-ass solution. So the whole solution took me a little bit over 4 hours with tests and documentation.
