# Class holds coordinate in 2D space.
class Coordinate

  #x and y coordinates
  attr_reader :x, :y
  
  #constructor takes x and y coordinates
  def initialize(x,y)
    @x,@y = x,y
  end

  #result of this metod is new coordinate with x and y equal to addition of
  #this object and argument
  def add(coordinate)
    new_x = @x + coordinate.x
    new_y = @y + coordinate.y
    new_coordinate = Coordinate.new(new_x,new_y)
  end
  
  #methods takes cardinal direction as a parameter and returns coordinate which equals
  #one step (from [0,0]) in given direction.
  #raies error if direction is unknown
  def self.step_addition_for(direction)
    case direction
    when :north
      return Coordinate.new(0,1)
    when :east
      return Coordinate.new(1,0)
    when :south
      return Coordinate.new(0,-1)
    when :west
      return Coordinate.new(-1,0)
    else
      raise 'Unknown direction'
    end
  end

  #equals methods
  def ==(o)
    o.class == self.class && o.x == @x && o.y == @y
  end
  alias_method :eql?, :==
end
