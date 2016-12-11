Toy Robot Simulator
===================
This repository contains simple Ruby [CLI](http://en.wikipedia.org/wiki/Command-line_interface) app, which represents toy robot simulator. The exact specification on which base is this application made can be found in a `/SPECIFICATION.md` file. This concrete implementation should display all possible qualities of mature software development process including:

* Well thought top level application design.
* Easy to understand and maintainable code.
* Proper tests ensuring that the application is fully functional.
* Good work with VCS. In this case GIT.
* Easy-to-understand documentation in both RDoc and written forms.

More information about design decisions which were made during writing this app can be found in a `/ARCHITECTURE.md` file.

Prerequisites
-------------
This program was developed under Ruby 2.1.1, but every version of Ruby higher than 1.9 should be sufficient.
You need to have installed Bundler in version 1.6 or higher.
Running the application
-----------------------
1. Firstly clone this repository.
2. Change directory to it `cd ruby-toy-robot`

Install all necessary gems

    bundle install

All tests are executed by command:

    bundle exec rspec


Now you can choose 2 ways how to run the actual robot program.

    #install gem
    rake install
    ruby_robot

or

    #run script without installation
    ./bin/ruby_robot

Either way you can run the app with stdin as an input (you can press `CTRL+D` to end the input):

    ruby_robot
    
Or use a file with commands as an input:

    ruby_robot [path to source file]
