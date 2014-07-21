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
end
