require 'spec_helper'

describe Table do

  let(:table){Table.new(4,4)}

  context :is_on_table do
    it 'is true if coordinate is on the table' do
      #corners
      expect(table.is_on_table(Coordinate.new(0,0))).to be true
      expect(table.is_on_table(Coordinate.new(4,4))).to be true
      expect(table.is_on_table(Coordinate.new(0,4))).to be true
      expect(table.is_on_table(Coordinate.new(4,0))).to be true
      #random point inside
      expect(table.is_on_table(Coordinate.new(2,1))).to be true
    end

    it 'is false if coordinate is outside of the table' do
      expect(table.is_on_table(Coordinate.new(-1,0))).to be false
      expect(table.is_on_table(Coordinate.new(5,0))).to be false
      expect(table.is_on_table(Coordinate.new(0,-1))).to be false
      expect(table.is_on_table(Coordinate.new(0,5))).to be false
    end
  end
end
