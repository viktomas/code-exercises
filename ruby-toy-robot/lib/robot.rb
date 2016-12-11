#Robot class is a simple state holder for robot's position
#and direction on the table.
class Robot

  #Table on which robot is placed, its coordinate and direction
  attr_reader :table, :coordinate, :direction
  
  #initialize robot which is not yet on the table
  #none of method arguments can be nil
  def put_on_table(table, coordinate, direction)
    raise 'nil validation error' if table.nil? || coordinate.nil? || direction.nil?
    @table = table
    self.coordinate=coordinate
    self.direction=direction
  end

  #assigns a coordinate to the robot
  #robot has to be on a table
  def coordinate=(coordinate)
    raise 'not on table yet' unless @table
    raise 'out of table' unless table.is_on_table(coordinate)
    @coordinate = coordinate
  end

  #assigns direction to the robot
  #robot has to be on a table
  def direction=(direction)
    raise 'not on table yet' unless @table
    @direction = direction
  end
  
end
