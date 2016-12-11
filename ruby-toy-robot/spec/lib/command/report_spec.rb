require 'spec_helper'

describe Command::Report do

  let(:robot) { Robot.new }

  context '#initialize' do
    it 'takes robot and output as an argument' do
      Command::Report.new(robot,STDOUT)
    end
  end

  context '#execute' do

    def test_output(x,y,direction,output)
      robot.put_on_table(Table.new(4,4),Coordinate.new(x,y),direction)
      out = double('out')
      expect(out).to receive(:puts).with(output)
      command = Command::Report.new(robot,out)
      command.execute
    end


    it 'writes robots position to output' do
      test_output(0,0,:north,"0,0,NORTH")
      test_output(2,4,:south,"2,4,SOUTH")
      test_output(1,3,:east,"1,3,EAST")
      test_output(4,2,:west,"4,2,WEST")
    end

    it "doesn't write anything if robot is not on a table" do
      out = double('out')
      command = Command::Report.new(robot,out)
      expect(out).to_not receive(:puts)
      command.execute
    end
  end

end
