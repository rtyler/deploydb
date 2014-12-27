import deploydb.cucumber.AppHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before {
    startAppWithConfiguration('deploydb.cucumber.yml')
}

After {
    stopApp()
}

World {
  new AppHelper()
}
