package com.laraguzman.tribalproofactivity.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseApplication: AppCompatActivity() {

    companion object {
        lateinit var instance : BaseApplication
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance= this
    }
}