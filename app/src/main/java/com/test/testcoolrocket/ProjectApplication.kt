package com.test.testcoolrocket

import android.app.Application
import com.test.testcoolrocket.di.Scopes
import com.test.testcoolrocket.di.modules.AppModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

class ProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initToothpick() {
        Toothpick.setConfiguration(
            if (BuildConfig.DEBUG) Configuration.forDevelopment()
            else Configuration.forProduction()
        )
        Toothpick.openScope(Scopes.APP).installModules(AppModule(this))
    }
}