package com.example.gargabesorter

import android.app.Application
import com.example.gargabesorter.di.components.AppComponent
import com.example.gargabesorter.di.components.DaggerAppComponent

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(applicationContext)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
        val gameComponent get() = appComponent.screenComponent()
    }
}