require 'spec_helper'

describe Robot do

  let(:robot){Robot.new}
  let(:table){Table.new(4,4)}
  let(:coordinate){Coordinate.new(0,0)}

  context '#put_on_table' do
    it 'should check that arguments are valid' do
      expect {robot.put_on_table(nil,nil,nil)}.to raise_error
      expect {robot.put_on_table(table, Coordinate.new(4,6), :north)}.to raise_error
    end

    it 'should be on the table afterwards' do
      robot.put_on_table(table,coordinate,:north)
      expect(robot.table).to eq(table)
      expect(robot.coordinate).to eq(coordinate)
      expect(robot.direction).to eq(:north)
    end
  end

  context '#coordinate' do
    it 'should not assign coordinate if robot does not have a table' do
      expect {robot.coordinate=coordinate}.to raise_error
    end

    it 'should not assign coordinate outside of a table' do
      robot.put_on_table(table,coordinate,:north)
      expect {robot.coordinate=Coordinate.new(6,2)}.to raise_error
    end

    it 'should assign valid coordinate' do
      robot.put_on_table(table,coordinate,:north)
      coordinate=Coordinate.new(2,2)
      robot.coordinate=coordinate
      expect(robot.coordinate).to eq(coordinate)
    end
  end

  context '#direcion' do
    it 'should not assign nil direction' do
      expect {robot.direction=:north}.to raise_error
    end

    it 'should assign valid direction' do
      robot.put_on_table(table,coordinate,:north)
      robot.direction=:south
      expect(robot.direction).to eq(:south)
    end
  end
end
