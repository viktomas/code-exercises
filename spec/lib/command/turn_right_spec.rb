require 'spec_helper'

describe Command::TurnRight do

  let(:robot) { Robot.new }

  it 'turns robot in the right direction' do
    robot.put_on_table(Table.new(4,4), Coordinate.new(0,0), :north)
    command = Command::TurnRight.new(robot)

    command.execute
    expect(robot.direction).to eq(:east)

    command.execute
    expect(robot.direction).to eq(:south)

    command.execute
    expect(robot.direction).to eq(:west)

    command.execute
    expect(robot.direction).to eq(:north)
  end
end
