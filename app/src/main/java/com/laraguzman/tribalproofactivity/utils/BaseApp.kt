package com.laraguzman.tribalproofactivity.utils

import android.app.Application

@SuppressWarnings("all")
class BaseApp : Application() {
    companion object {
        lateinit var instance: BaseApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}