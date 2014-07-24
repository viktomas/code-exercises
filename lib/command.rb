#This module works as a namespace for all commands
module Command
  
  Dir[File.expand_path('../command/*.rb', __FILE__)].each{|f| require f}
  # empty command represents invalid input
  # by specification invalid input means no action
  class Empty

    #empty execute action for invalid input
    def execute
    end

  end

end
