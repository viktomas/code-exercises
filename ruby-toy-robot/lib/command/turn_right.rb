# because of inheritance we have to specifically require
require File.dirname(__FILE__)+'/turn.rb'

# Command which turns robot in right hand direction
class Command::TurnRight < Command::Turn

  # returns hash with next directions in right turning
  def next_direction
    {north: :east,
     east: :south,
     south: :west,
     west: :north}
  end

end
