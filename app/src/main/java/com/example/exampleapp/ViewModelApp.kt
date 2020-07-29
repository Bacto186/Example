package com.example.exampleapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exampleapp.database.DaoApp
import com.example.exampleapp.database.EntityApp
import com.example.exampleapp.database.RoomApp
import kotlinx.coroutines.*

class ViewModelApp(dataDaoApp: DaoApp, application: Application) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var app = MutableLiveData<EntityApp>()
    val daoApp = dataDaoApp
    val apps = daoApp.getAllApp()

    fun insertApp(entityApp: EntityApp) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                daoApp.insertApp(entityApp)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
