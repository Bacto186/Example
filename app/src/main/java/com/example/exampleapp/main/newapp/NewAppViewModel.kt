package com.example.exampleapp.main.newapp

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.exampleapp.database.AppDao
import com.example.exampleapp.database.App
import kotlinx.coroutines.*

class NewAppViewModel(val datasource: AppDao, val application: Application) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val daoApp = datasource

    fun onInsert(app: App) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                daoApp.insertApp(app)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}