require 'spec_helper'

describe Coordinate do

  let(:coordinate){Coordinate.new(5,6)}

  context 'initialize' do
    it 'should have x and y as accessible attributes' do
      expect(coordinate.x).to eq(5)
      expect(coordinate.y).to eq(6)
    end

    it 'should not be possible to change initialized coordinate' do
      expect {coordinate.x = 1}.to raise_error
      expect {coordinate.p = 1}.to raise_error
    end
  end

  context 'add' do
    it 'should take coordinate as an argument' do
      coordinate.add(Coordinate.new(0,0))
    end

    it 'should stay intact after method call' do
      coordinate.add(Coordinate.new(1,1))
      expect(coordinate.x).to eq(5)
      expect(coordinate.y).to eq(6)
    end

    it 'should return new coordinate as addition of object and argument' do
      addition = coordinate.add(Coordinate.new(1,-1))
      expect(addition.x).to eq(6)
      expect(addition.y).to eq(5)
    end
  end

  context 'step_addition_for' do
    it 'returns proper values for valid parameters' do
      expect(Coordinate.step_addition_for(:north)).to eq(Coordinate.new(0,1))
      expect(Coordinate.step_addition_for(:east)).to eq(Coordinate.new(1,0))
      expect(Coordinate.step_addition_for(:south)).to eq(Coordinate.new(0,-1))
      expect(Coordinate.step_addition_for(:west)).to eq(Coordinate.new(-1,0))
    end

    it 'raises error when unknown direction is passed' do
      expect {Coordinate.step_addition_for(:unknown)}.to raise_error
    end
  end
end
