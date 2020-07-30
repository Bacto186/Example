package com.example.exampleapp.main.newapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exampleapp.database.AppDao
import java.lang.IllegalArgumentException

class NewAppViewModelFactory(private val datasource: AppDao, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewAppViewModel::class.java)) {
            return NewAppViewModel(
                datasource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }

}