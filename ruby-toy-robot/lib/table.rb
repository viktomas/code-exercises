#Class works as restriction of space for robots movement
class Table

  #constructor takes maximal dimensoins of table
  #robot has to move between 0 and max_ coordinate
  def initialize(max_x,max_y)
    @max_x,@max_y = max_x, max_y
  end

  #is given coordinate on the table?
  def is_on_table(coordinate)
    is_on_x = 0 <= coordinate.x && coordinate.x <= @max_x
    is_on_y = 0 <= coordinate.y && coordinate.y <= @max_y
    is_on_x && is_on_y
  end

end
