package com.example.test_task_4.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

@HiltAndroidApp
class TaskApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig.newConfigBuilder(Secret.API_KEY).build()
        // Initializing the AppMetrica SDK.
        AppMetrica.activate(this, config)
    }
}

