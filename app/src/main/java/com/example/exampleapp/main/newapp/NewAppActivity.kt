package com.example.exampleapp.main.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.exampleapp.R
import com.example.exampleapp.database.AppDao
import com.example.exampleapp.database.App
import com.example.exampleapp.database.AppDatabase
import kotlinx.android.synthetic.main.activity_new_app.*

class NewAppActivity : AppCompatActivity() {

    var viewModelNewApp: NewAppViewModel? = null
    lateinit var dataAppDao: AppDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_app)

        dataAppDao = AppDatabase.getData(application).dao
        val viewModelFactoryNewApp =
            NewAppViewModelFactory(
                dataAppDao,
                application
            )
        viewModelNewApp = ViewModelProviders.of(this, viewModelFactoryNewApp).get(NewAppViewModel::class.java)
    }

    fun saveNewApp(view: View) {

        var name: String = edtNameApp.text.toString()
        var descrip: String = edtDescriptionApp.text.toString()
        var app: App = App(nameApp = name, descriptionApp = descrip)

        viewModelNewApp?.onInsert(app)
        finish()
    }

    fun cancelNewApp(view: View) {
        finish()
    }
}