# Command for writing report to output
class Command::Report

  # constructor takes robot and output as parameters
  def initialize(robot,out)
    @robot = robot
    @out = out
  end

  # writes robot's position and direction to output
  # only if robot is on a table
  def execute
    return unless @robot.table
    x = @robot.coordinate.x
    y = @robot.coordinate.y
    direction = @robot.direction.to_s.upcase
    @out.puts("#{x},#{y},#{direction}")
  end
end
