# Class mented as a parrent for all turning commands
# Ancestors have to implement only next_direction method
class Command::Turn

  # constructor takes robot as an argument
  def initialize(robot)
    @robot=robot
  end

  # turns robot
  # works only if robot is already on a table
  def execute
    return unless @robot.table
    @robot.direction=next_direction[@robot.direction]
  end

  # abstract method used for turning
  # should return hash in following format:
  # next_direction[:direction]=:next_direction
  #
  # Example for turning right:
  # next_direction[:north] returns :east
  def next_direction
    raise 'Subclasses have to implement this method'
  end
end
