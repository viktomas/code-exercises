# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'ruby_toy_robot/version'

Gem::Specification.new do |spec|
  spec.name          = "ruby_toy_robot"
  spec.version       = RubyToyRobot::VERSION
  spec.authors       = ["Tomas Vik"]
  spec.email         = ["vicek22@gmail.com"]
  spec.summary       = %q{Excersize for displaying mature software development}
  spec.homepage      = ""
  spec.license       = "MIT"

  spec.files         = `git ls-files -z`.split("\x0")
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib"]

  spec.add_development_dependency "bundler", "~> 1.6"
  spec.add_development_dependency 'rspec'
  spec.add_development_dependency 'guard-rspec'
  spec.add_development_dependency "rake"
end
