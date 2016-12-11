Architecture of Toy Robot Simulator
===================================
This document serves as explanation of architectonic decisions made during the process of implementing this assignment.

Core - commands and robot
-------------------------
After first reading of `SPECIFICATION.md` there is obvious that main theme of the application will be sending commands to the robot and solving problems connected to the limited space of table. Command is naturally main design pattern used during implementation. Commands contain all business logic connected to moving robot (Receiver). Parser works here as a Client and Invoker in one. There was no need for separate Invoker class, because it would only wrap one `#execute()` call on the command object.

Every command has also `#isValid()` method, which returns `true` if command can be executed and `false` otherwise. Validity of command is checked before every execution and non valid commands are not executed. Robot contains basic validations which enforce the valid state of the robot at any time (robot in on the table, if robot is being put on the table its coordinates and direction has to be set). But those validations should never be used (understand never throw exception) because non valid commands are not executed and hence there is no invalid method call.

Table is assigned to the robot and works only as a holder of allowed space for robot. (By specification given to 5x5).

Parsing of commands
-------------------
Mechanism of parsing commands is based on theirs line characteristics (1 line = 1 command). Command parsing is explained by following diagram:

    Input =(string)=> Lexer =(raw command)=> Parser =(actual command)=>
    
Where Input basically wraps either FileReader or InputStreamReader which reads System.in. Lexer then reads this one String line ad transforms it into a token (raw command). Parser reads raw command and creates actual command, which has references on all objects it needs (robot, output, table).

Invalid input string can produce raw command with `CommandType#INVALID`. Parser creates from invalid raw command `Command.VOID` which has empty body of `#execute()` method and hence very execution of VOID command is defacto ignoring it. That was the clearest way how to achieve ignoring commands and it is possible because specification allows ignoring commands.

Wrapping it all up with Spring context
--------------------------------------
Simple and easy way how to assemble well developed application is to use Spring Context. Big advantage of it is that one or few XML files can provide complete view on how the application is build. In this case `Main#main()` method firstly determines if user wishes to use stdin or file as a command source and then creates context either from `stdin-context.xml` or `file-context.xml` definition. Both of them include `main-context.xml` which contains the core information about how the application is assembled.

To sum up, Spring provides high quality Dependency Injection which makes components of the application even more loosely coupled.

Little things - how to avoid unnecessary if's and blocks
--------------------------------------------------------
This chapter contains small architectonic decisions I'm proud of, but they didn't specifically belong to any of the previous chapter.

### Direction
Every constant in this enumeration contains simple vector with size one (`getStepAddition()`), which can be added to every `Coordinate` and thus make step of size one in given direction. This mechanism is obviously used in `MoveCommand` which makes the implementation really simple.

### TurnCommand
`TurnCommand` is abstract predecessor of `TurnLeftCommand` and `TurnRightCommand`. Only method that its subclasses have to implement is the `#nextDirectionMap()` method which enables not only left an right turn, but also for example 180 degrees turn or always west turn.

### Runtime exceptions
I personally hate checked exceptions on places, where client doesn't have a chance to recover from them. That is reason why Input and Output interfaces have methods declarations without checked exceptions and their implementations has to convert them to runtime exceptions. It makes the code much cleaner.

### Immutable objects
It is good practice to make all possible object immutable. I simulated it on Table and Coordinate. Those objects one created can't be changed an for example addition of coordinates creates a new coordinate rather than altering old one.

It wouldn't be a bad thought to rewrite Robot to an immutable object which would mate almost all commands also immutable.

One thing I'm not happy with
----------------------------
The only design thing which pained me to write into my keyboard was argument handling in `RawCommand`. Which is basically token output from `Lexer` There is a `List<Object>` filled with arguments and then `Parser` takes this list and statically types two objects to `Integer` and third to `Direction`. There is no chance that static typing would fail here. The only component of the system which creates raw commands is `Lexer` and it creates them always right.

I chose this approach in the name of feature extensibility of commands. I didn't want to chain myself to two integer one direction format of argument.