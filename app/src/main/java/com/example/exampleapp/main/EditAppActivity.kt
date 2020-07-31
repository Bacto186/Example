package com.example.exampleapp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.exampleapp.R
import com.example.exampleapp.database.App
import com.example.exampleapp.database.AppDao
import com.example.exampleapp.database.AppDatabase
import kotlinx.android.synthetic.main.activity_edit_app.*
import kotlinx.coroutines.*

class EditAppActivity : AppCompatActivity() {

    private var id: Int = 0
    private var name: String? = ""
    private var description: String? = ""
    private val job:Job = Job()
    private lateinit var uiScope:CoroutineScope
    private lateinit var data: AppDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_app)
        this.setFinishOnTouchOutside(false)

        uiScope = CoroutineScope(Dispatchers.Main + job)
        data = AppDatabase.getData(application).dao

        val intent = intent
        name = intent.getStringExtra("name_app")
        description = intent.getStringExtra("description_app")
        id = intent.getIntExtra("id_app", 0)

        edtNameEdit.setText(name)
        edtDescriptionEdit.setText(description)
    }

    fun cancelEditApp(view: View) {
        finish()
    }
    fun saveEditApp(view: View) {
        name = edtNameEdit.text.toString()
        description = edtDescriptionEdit.text.toString()
        val app: App = App(id, name, description)
        uiScope.launch {
            withContext(Dispatchers.IO){
                data.update(app)
            }
        }
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}