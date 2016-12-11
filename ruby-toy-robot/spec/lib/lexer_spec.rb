require 'spec_helper'

describe Lexer do

  context '#parse_line' do

    # helper method to validate right token type of parsed line
    def expect_to_parse(line,token_type)
      token = Lexer.parse_line(line)
      expect(token.type).to eq(token_type)
    end

    it 'should parse place command' do
      token = Lexer.parse_line("PLACE 0,0,NORTH")
      expect(token.type).to eq(:place)
      expect(token.args).to eq([0,0,:north])

      token = Lexer.parse_line('PLACE 3,4,EAST')
      expect(token.type).to eq(:place)
      expect(token.args).to eq([3,4,:east])
    end

    it 'should parse move command' do
      expect_to_parse("MOVE",:move)
    end

    it 'should parse turn right command' do
      expect_to_parse("RIGHT",:right)
    end

    it 'should parse turn left command' do
      expect_to_parse("LEFT",:left)
    end

    it 'should parse report command' do
      expect_to_parse("REPORT",:report)
    end

    it 'should parse every other line as invalid' do
      expect_to_parse(" REPORT",:invalid)
      expect_to_parse("MOVE ",:invalid)
      expect_to_parse("LEFTMOVE",:invalid)
      expect_to_parse("PLACE0,0,NORTH",:invalid)
      expect_to_parse("PLACE 0,0,SOUTHWEST",:invalid)
      expect_to_parse("RIGHTPLACE",:invalid)
      expect_to_parse("right",:invalid)
    end
        
  end

end
