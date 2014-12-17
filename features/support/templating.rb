require 'rubygems'
require 'liquid'

module DeployDB
  module Test
    def render_string(buffer, opts={})
      ::Liquid::Template.parse(buffer).render(template_vars.merge(opts))
    end

    def template_vars
      @template_vars ||= {}
    end
  end
end

World(DeployDB::Test)
