package com.example.exampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.exampleapp.database.DaoApp
import com.example.exampleapp.database.RoomApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = AdapterRecyclerView()
        recyclerView.adapter = adapter

        val data: DaoApp = RoomApp.getData(application).dao
        val viewModelFactoryApp = ViewModelFactoryApp(data, application)
        val viewModelApp = ViewModelProviders.of(this, viewModelFactoryApp).get(ViewModelApp::class.java)

//        var db = data.getAllApp().value
//        Log.d("aaa", "data ban dau la: $db ")

        viewModelApp.apps.observe(this, Observer { it?.let { adapter.apps = it } })
//        Log.d("aaa", "data observer la: $db ")
    }
    fun addNewApp(view: View) {
        val newAppIntent = Intent(this, NewAppActivity::class.java)
        startActivity(newAppIntent)
    }
}