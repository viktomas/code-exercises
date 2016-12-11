# Command for placing robot on the table
class Command::Place

  # initializes place command
  # params:
  # - robot - robot which will be placed
  # - table - table on which robot will be placed
  # - coordinate - robot's coordinate on the table
  # - direction - direction robot is facing
  def initialize(robot,table,coordinate,direction)
    @robot = robot
    @table = table
    @coordinate = coordinate
    @direction = direction
  end

  # places robot on the table
  # only if robot is not on some table yet
  # only if coordinate is on the table
  def execute
    if @table.is_on_table(@coordinate) && !@robot.table
      @robot.put_on_table(@table,@coordinate,@direction)
    end
  end
end
