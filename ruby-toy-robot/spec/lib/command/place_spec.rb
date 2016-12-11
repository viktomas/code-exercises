require 'spec_helper'

describe Command::Place do

  let(:robot) { Robot.new }
  let(:table) { Table.new(4,4) }
  let(:coordinate) { Coordinate.new(2,2) }

  context '#initialize' do
    it "takes 4 arguments to initialize" do
      Command::Place.new(robot,table,coordinate,:north)
    end
  end

  context '#execute' do
    it 'puts robot on table with right coordinate and direction' do
      command = Command::Place.new(robot,table,coordinate,:north)
      command.execute()
      expect(robot.table).to eq(table)
      expect(robot.coordinate).to eq(coordinate)
      expect(robot.direction).to eq(:north)
    end

    it 'does nothing if coordinate is outside of a table' do
      command = Command::Place.new(robot,table,Coordinate.new(5,2),:north)
      command.execute()
      expect(robot.table).to be_nil
      expect(robot.coordinate).to be_nil
      expect(robot.direction).to be_nil
    end

    it 'does nothing if robot already has a table' do
      robot.put_on_table(table,coordinate,:north)
      command = Command::Place.new(robot,table,Coordinate.new(4,2),:south)
      expect(robot.coordinate).to eq(coordinate)
      expect(robot.direction).to eq(:north)
    end
  end
end
