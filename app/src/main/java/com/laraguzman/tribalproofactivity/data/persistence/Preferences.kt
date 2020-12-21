package com.laraguzman.tribalproofactivity.data.persistence

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    var PREFS_NAME = "com.laraguzman.tribalproofactivity.sharedpreferences"
    val SHARED_NAME = "unsplash_array"
    val prefs : SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) {
            prefs.edit().putString(SHARED_NAME, value).apply()
        }

    fun clear(){
        prefs.edit().remove(SHARED_NAME).apply()
    }
}