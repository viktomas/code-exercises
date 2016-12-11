require 'spec_helper'

describe Command::Move do

  let(:robot) { Robot.new() }

  before(:each) do
    robot.put_on_table(Table.new(4,4),Coordinate.new(0,0),:north)
  end

  context '#initialize' do
    it 'takes robot as a parameter' do
      Command::Move.new(robot)
    end
  end

  context '#execute' do
    it 'moves robot one step in his direction' do
      command = Command::Move.new(robot)
      command.execute()
      expect(robot.coordinate).to eq(Coordinate.new(0,1))
      expect(robot.direction).to eq(:north)
    end

    it 'does nothing when robot would step out of table' do
      #bottom
      robot.direction = :south
      command = Command::Move.new(robot)
      command.execute()
      expect(robot.coordinate).to eq(Coordinate.new(0,0))
      #left
      robot.direction = :west
      command = Command::Move.new(robot)
      command.execute()
      expect(robot.coordinate).to eq(Coordinate.new(0,0))
      #top
      robot.coordinate = Coordinate.new(4,4)
      robot.direction = :north
      command = Command::Move.new(robot)
      command.execute()
      expect(robot.coordinate).to eq(Coordinate.new(4,4))
      #right
      robot.coordinate = Coordinate.new(4,4)
      robot.direction = :east
      command = Command::Move.new(robot)
      command.execute()
      expect(robot.coordinate).to eq(Coordinate.new(4,4))
    end

    it 'does nothing when robot is not on a table' do
      levitating_robot = Robot.new
      command = Command::Move.new(levitating_robot)
      command.execute()
      expect(levitating_robot.table).to be_nil
    end
  end

end
