Dir[File.dirname(__FILE__) + '/**/*.rb'].each {|file| require file }

#main module of gem ruby_toy_robot
module RubyToyRobot

  # App class holds static method for running whole application
  class App

    # method takes input and output as argument and runs robot application
    def self.run(args,out=STDOUT)
      robot = Robot.new
      table = Table.new(4,4)
      parser = Parser.new(robot,table,out)

      input = input(args)
      
      while(line = input.gets)
        #remove <CR> from end of the line
        line = line.chomp
        token = Lexer.parse_line(line)
        command = parser.parse(token)
        command.execute
      end
    end

    # method optains input
    # - file if it is specified
    # - STDIN otherwise
    def self.input(args)
      if args.first
        open(args.first)
      else
        STDIN
      end
    end
  end
end
