require 'rubygems'

$LOAD_PATH.unshift(File.expand_path(File.dirname(__FILE__) + '/../../src'))

require 'deploydb/server/app'


module DeployDB
  module Test
    def app
      DeployDB::Server::App
    end
  end
end
