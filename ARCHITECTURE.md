Architecture of Toy Robot Simulator
===================================
This document serves as an explanation of architectonic decisions made during the process of implementing this assignment.

Core - commands and robot
-------------------------
After first reading of `SPECIFICATION.md` there is obvious that the main theme of the application will be sending commands to the robot and solving problems connected to the limited space of a table. Command is naturally main design pattern used during implementation. Commands contain all business logic connected to moving the robot (Receiver). Parser works here as a Client which understands lexer's tokens. `RubyToyRobot::App` then takes created commands and work as an Invoker.

Validity of command is checked before every execution and non valid commands are not executed. Robot contains basic validations which enforce the valid state of the robot at any time (ie. robot in on the table). But those validations should never be used (understand never throw an exception) because non-valid commands are not executed and hence there is no invalid method call.

Table is assigned to the robot and works only as a holder of allowed space for the robot. (By specification given to 5x5).

Parsing of commands
-------------------
Mechanism of parsing commands is based on their line characteristics (1 line = 1 comment). Command parsing is explained by following diagram:

    input =(string)=> Lexer =(token)=> Parser =(actual command)=> RubyToyRobot::App
   
Where input is either `STDIN` or open file. Ruby has already provided unified interface (thanks Duck Typing) and only needed method `#gets`. Lexer gets one line as a parameter and transforms it into a token. Parser gets this token as a parameter and creates actual command, which has references to all objects it needs (robot, output, table).

Invalid input string can produce token with `:invalid` type. Parser creates from invalid token `Command::Empty` which has empty body of `#execute` method and hence the very execution of Empty command is defacto ignoring it. That was the clearest way how to achieve ignoring commands and it is possible because the specification allows ignoring commands.

Assembling it all together
--------------------------
In Ruby is not a good practice to assemble objects together with configuration files. I avoided this approach by assembling app in `RubyToyRobot::App` class. `#run` method is then called from ruby_robot script.

Little things
--------------------------------------------------------
This chapter contains small architectonic decisions I'm proud of, but they didn't specifically belong to any of the previous chapters.

### Lexer.parse_line
This is exactly the kind of method which can demonstrate Ruby's strengths. The method is clearly readable, and on 15 lines of source code it contains relatively complex logic of parsing all commands for the robot. The whole Lexer class has 41 lines of source code including comments. My Java example had the same logic written in 282 lines of source code (Lexer interface, Simple implementation and 2 helper classes)

### TurnCommands
`Turn` is abstract predecessor of `TurnLeft` and `TurnRight`. The only method that its subclasses have to implement is the `#next_direction` method which enables not only left and right turn, but also for example 180 degrees turn or always west turn. Ruby commands which inherit from Turn are extremely simple and they don't even have to implement constructor.


What am I uncentain about
----------------------------
I chose not to check arguments of methods for nil. It is usually good practice (in java?) to validate the parameters of all methods which can be called by other people. Reason is, that every NPE will be discovered exactly on the place where the error actually is and not somewhere deeper in the method call.

I chose not to check for nil because of better readability of a code and because there is no way that nil would get into core of this example.

I even [asked question on StackOverflow](http://stackoverflow.com/questions/24864839/validating-method-arguments-for-nil)
