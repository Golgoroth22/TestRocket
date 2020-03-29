package com.test.testcoolrocket.di.modules

import com.test.testcoolrocket.ui.activities.MainActivity
import com.test.testcoolrocket.ui.activities.interfaces.SaveChartCallback
import toothpick.config.Module

class DrawerModule(activity: MainActivity) : Module() {
    init {
        bind(SaveChartCallback::class.java).toInstance(activity)
    }
}