require 'spec_helper'

describe Command::Turn do

  let(:robot) { Robot.new }

  context '#initialize' do
    it 'takes robot as an argument' do
      Command::Turn.new(robot)
    end
  end

  context '#execute' do
    it "won't execute if robot isn't on a table" do
      #it would throw exception on #next_direction
      Command::Turn.new(robot).execute
    end

    it "raises error when executed on valid robot" do
      robot.put_on_table(Table.new(4,4), Coordinate.new(0,0), :north)
      command = Command::Turn.new(robot)
      expect {command.execute}.to raise_error
    end
  end

  it "raise an error when #next_direction is called" do
    command = Command::Turn.new(robot)
    expect {command.next_direction}.to raise_error
  end

end
