import deploydb.cucumber.AppHelper
import deploydb.cucumber.ModelHelper

import org.joda.time.DateTime
import org.joda.time.DateTimeUtils

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before {
    startAppWithConfiguration('deploydb.cucumber.yml')
}

After {
    stopApp()
}

Before('@freezetime') {
    DateTimeUtils.setCurrentMillisFixed(DateTime.now().millis)
}

After('@freezetime') {
    DateTimeUtils.setCurrentMillisSystem()
}

World {
  new AppHelper()
}

World {
    new ModelHelper()
}
