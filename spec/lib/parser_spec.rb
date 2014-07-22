require 'spec_helper'
require 'ostruct'

describe Parser do

  let(:robot) { Robot.new }

  context '#initialize' do
    it 'takes robot, table and output as arguments' do
      Parser.new(robot,Table.new(4,4),double('out'))
    end
  end

  context '#parse' do

    let(:out) { double('out') }
    let(:table) { Table.new(4,4) }
    let(:parser) { Parser.new(robot,table,out) }
    let(:coordinate) { Coordinate.new(0,0) }

    def execute_token(token_type)
      robot.put_on_table(table,coordinate,:north)
      token = OpenStruct.new(type: token_type)
      command = parser.parse(token)
      command.execute
    end


    it 'parses place' do
      token = OpenStruct.new(type: :place, args: [0,0,:north])
      command = parser.parse(token)
      command.execute
      expect(robot.table).to eq(table)
      expect(robot.coordinate).to eq(coordinate)
      expect(robot.direction).to eq(:north)
    end

    it 'parses move' do
      execute_token(:move)
      expect(robot.coordinate).to eq(Coordinate.new(0,1))
    end

    it 'parses right' do
      execute_token(:right)
      expect(robot.direction).to eq(:east)
    end

    it 'parses left' do
      execute_token(:left)
      expect(robot.direction).to eq(:west)
    end

    it 'parses report' do
      expect(out).to receive(:puts).with("0,0,NORTH")
      execute_token(:report)
    end

    it 'parses invalid' do
      execute_token(:invalid)
      expect(robot.table).to eq(table)
      expect(robot.coordinate).to eq(coordinate)
      expect(robot.direction).to eq(:north)
    end
  end
end
