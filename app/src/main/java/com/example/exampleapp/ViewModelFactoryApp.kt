package com.example.exampleapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exampleapp.database.DaoApp
import com.example.exampleapp.database.RoomApp
import java.lang.IllegalArgumentException

class ViewModelFactoryApp(private val dataDaoApp: DaoApp, private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelApp::class.java)) {
            return ViewModelApp(dataDaoApp, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}