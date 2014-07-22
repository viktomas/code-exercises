# because of inheritance we have to specifically require
require File.dirname(__FILE__)+'/turn.rb'

# Command which turns robot in left hand direction
class Command::TurnLeft < Command::Turn

  # returns hash with next directions in left turning
  def next_direction
    {north: :west,
     west: :south,
     south: :east,
     east: :north}
  end

end
