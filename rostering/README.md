Rostering system
================
This repository contains a simple java [CLI](http://en.wikipedia.org/wiki/Command-line_interface) app, which represents a simple rostering system. The exact specification on which base is this application made can be found in a `/SPECIFICATION.md` file. This concrete implementation should display all possible qualities of mature software development process including:

* Well thought top level application design.
* Easy to understand and maintainable code.
* Proper tests ensuring that the application is fully functional.
* Good work with VCS. In this case GIT.
* Easy-to-understand documentation in both JavaDoc and written forms.

More information about design decisions which were made during writing this app can be found in a `/ARCHITECTURE.md` file.

Prerequisites
-------------
To successfully run this application, the host system must have [Java 1.7](https://www.java.com/en/download/) or higher installed.
Whole app is created as a [Maven](http://maven.apache.org/what-is-maven.html) project and host system has to have Maven 3 installed. Information how to install those technologies can be found on their pages or google.

Running the application
-----------------------
1. Firstly clone this repository.
2. Change directory to it `cd rostering`

All tests are executed by the command:

    mvn test
   
Or you can create a single jar executable with command

    mvn compile assembly:single

Running this command will create a file `target/rostering-system-1.0-SNAPSHOT-jar-with-dependencies.jar`. This file can be used in two ways.

Running app with stdin as an input (you can press `CTRL+D` to end the input):

    java -jar rostering-system-1.0-SNAPSHOT-jar-with-dependencies.jar
   
    #or
   
    cat spec-example.csv | java -jar rostering-system-1.0-SNAPSHOT-jar-with-dependencies.jar
   
Or using a file with commands as an input:

    java -jar rostering-system-1.0-SNAPSHOT-jar-with-dependencies.jar -f [path to source file]

Input
-----
Application expects a CSV file on the input (examples are `/spec-example.csv` and `/trivial-example.csv`) and it throws
away first line as a header.