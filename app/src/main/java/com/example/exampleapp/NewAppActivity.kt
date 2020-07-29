package com.example.exampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.exampleapp.database.DaoApp
import com.example.exampleapp.database.EntityApp
import com.example.exampleapp.database.RoomApp
import kotlinx.android.synthetic.main.activity_new_app.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewAppActivity : AppCompatActivity() {

    var viewModelApp: ViewModelApp? = null
    lateinit var dataApp: DaoApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_app)

        dataApp = RoomApp.getData(application).dao
        val viewModelFactoryApp = ViewModelFactoryApp(dataApp, application)
        viewModelApp = ViewModelProviders.of(this, viewModelFactoryApp).get(ViewModelApp::class.java)
    }

    fun saveNewApp(view: View) {

        var name: String = edtNameApp.text.toString()
        var descrip: String = edtDescriptionApp.text.toString()
        var app: EntityApp = EntityApp(nameApp = name, descriptionApp = descrip)

        viewModelApp?.insertApp(app)
//            var databaseapp = dataApp.getAllApp().value
//            Log.d("aaa", "data sau add la: $databaseapp")
        finish()
    }

    fun cancelNewApp(view: View) {
        finish()
    }
}