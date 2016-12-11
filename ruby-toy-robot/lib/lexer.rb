require 'ostruct'

# Creates tokens from input lines
class Lexer

  # regex for validating and parsing place command
  PLACE_REGEX = /^PLACE (\d+),(\d+),(NORTH|EAST|SOUTH|WEST)$/

  # method responsible for parsing input lines
  # params:
  # - line - input string containing commands or invalid sequences
  # output:
  # - token with two properties #type and #args
  # - token has type invalid if input is not a valid command
  def self.parse_line(line)
    case(line)
    when PLACE_REGEX
      x,y,direction = line.match(PLACE_REGEX).captures
      token(:place, [x.to_i,y.to_i,direction.downcase.to_sym])
    when 'MOVE'
      token(:move)
    when 'RIGHT'
      token(:right)
    when 'LEFT'
      token(:left)
    when 'REPORT'
      token(:report)
    else
      token(:invalid)
    end
  end

  # creates token objects
  # instead of defining new class simple ostruct is returned
  # params:
  # - type - token type
  # - args - array with all arguments
  def self.token(type,args=[])
    OpenStruct.new(type: type, args: args)
  end
end
