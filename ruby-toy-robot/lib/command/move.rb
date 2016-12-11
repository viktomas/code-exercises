# Command for robot's movement
class Command::Move

  # initializes command
  # params:
  # - robot - robot which will move
  def initialize(robot)
    @robot = robot
  end

  # moves with robot
  # only if robot is on a table
  # only if movement won't put robot out of the table
  def execute
    return unless @robot.table
    step_addition = Coordinate.step_addition_for(@robot.direction)
    new_coordinate = @robot.coordinate.add(step_addition)
    if @robot.table.is_on_table(new_coordinate)
      @robot.coordinate = new_coordinate
    end
  end

end
