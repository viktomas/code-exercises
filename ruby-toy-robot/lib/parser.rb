require 'command'
# Parser transforms tokens from lexer into real commands
class Parser

  # constructor takes robot, then table for place command
  # and output for report command
  def initialize(robot,table,out)
    @robot = robot
    @table = table
    @out = out
  end

  # parse method transforms token into real command
  # params:
  # - token - output from lexer
  # returns:
  # - command - command which have method #execute
  def parse(token)
    case(token.type)
    when :place
      #expects 3 arguments
      x = token.args[0]
      y = token.args[1]
      direction = token.args[2]
      Command::Place.new(@robot,@table,Coordinate.new(x,y),direction)
    when :move
      Command::Move.new(@robot)
    when :left
      Command::TurnLeft.new(@robot)
    when :right
      Command::TurnRight.new(@robot)
    when :report
      Command::Report.new(@robot,@out)
    when :invalid
      Command::Empty.new
    end
  end
end
