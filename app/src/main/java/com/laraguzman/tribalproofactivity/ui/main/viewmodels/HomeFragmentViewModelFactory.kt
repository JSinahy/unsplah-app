package com.laraguzman.tribalproofactivity.ui.main.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeFragmentViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeFragmentViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}