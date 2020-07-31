package com.example.exampleapp.main

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exampleapp.database.AppDao
import com.example.exampleapp.database.App
import com.example.exampleapp.dicerollerapp.DiceRollerActivity
import kotlinx.coroutines.*

class AppViewModel(dataAppDao: AppDao, application: Application) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val mApplication = application

    private var app = MutableLiveData<App>()
    val daoApp = dataAppDao
    val apps = daoApp.getAllApp()

    fun onOpenApp(app: App) {
        val id: Int? = app.id
        Toast.makeText(mApplication, "click item $id", Toast.LENGTH_LONG).show()
        when (id) {
            8 -> onIntentApp(DiceRollerActivity())
        }
    }
    private fun onIntentApp(activity: Activity){
        val intentApp: Intent = Intent(mApplication, activity::class.java)
        intentApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mApplication.startActivity(intentApp)
    }

    fun onEdit(app: App) {
        var mApp: App = app
        var idApp = mApp.id
        var nameApp = mApp.nameApp
        var descriptionApp = mApp.descriptionApp

        val intent = Intent(mApplication, EditAppActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("name_app", nameApp)
        intent.putExtra("id_app", idApp)
        intent.putExtra("description_app", descriptionApp)
        mApplication.startActivity(intent)
    }

    fun onDelete(app: App) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                daoApp.delete(app)
            }
        }
    }

    fun onClear() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                daoApp.clear()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
