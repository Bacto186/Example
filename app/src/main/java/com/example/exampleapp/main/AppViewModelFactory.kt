package com.example.exampleapp.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exampleapp.database.AppDao
import java.lang.IllegalArgumentException

class AppViewModelFactory(private val dataAppDao: AppDao, private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(
                dataAppDao,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}