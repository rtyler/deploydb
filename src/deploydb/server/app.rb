require 'rubygems'
require 'sinatra/base'

module DeployDB
  module Server
    class App < Sinatra::Base
      get %r{/health(.json)?} do |format|
        if format == '.json'
          body '{}'
        else
          body 'Everything is OK'
        end
      end
    end
  end
end
