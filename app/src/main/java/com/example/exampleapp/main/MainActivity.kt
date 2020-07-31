package com.example.exampleapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.exampleapp.main.newapp.NewAppActivity
import com.example.exampleapp.R
import com.example.exampleapp.database.App
import com.example.exampleapp.database.AppDao
import com.example.exampleapp.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var viewModelApp: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data: AppDao = AppDatabase.getData(application).dao
        val viewModelFactoryApp =
            AppViewModelFactory(data, application)
        viewModelApp = ViewModelProviders.of(this, viewModelFactoryApp).get(AppViewModel::class.java)

        val adapter =
            AdapterRecyclerView(viewModelApp, this)
        recyclerView.adapter = adapter

        viewModelApp.apps.observe(this, Observer { it?.let { adapter.apps = it } })
    }
    fun addNewApp(view: View) {
        val newAppIntent = Intent(this, NewAppActivity::class.java)
        startActivity(newAppIntent)
    }
    fun onClearAllApp(view: View){
        viewModelApp.onClear()
    }

    override fun onItemClicked(app: App) {
        viewModelApp.onOpenApp(app)
    }
}