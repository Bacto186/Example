package com.example.exampleapp.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.exampleapp.R
import com.example.exampleapp.aboutmeapp.AboutMeActivity
import com.example.exampleapp.androidtrivia.AndroidTriviaActivity
import com.example.exampleapp.colormyviews.ColorMyViewsActivity
import com.example.exampleapp.dessertclicker.DessertClickerActivity
import com.example.exampleapp.dicerollerapp.DiceRollerActivity
import com.example.exampleapp.guesstheword.GuessTheWordActivity
import com.example.exampleapp.trackmysleep.TrackMySleepQualityActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var apps: ArrayList<App> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter =
            AdapterRecyclerView(this)

        apps.add(App(idApp = 0, nameApp = "Dice Roller", descriptorApp = "Get started"))
        apps.add(App(idApp = 1, nameApp = "About Me", descriptorApp = "Linear layout, Data binding"))
        apps.add(App(idApp = 2, nameApp = "Color My view", descriptorApp = "Constraint layout"))
        apps.add(App(idApp = 3, nameApp = "AndroidTrivia", descriptorApp = "Fragment, navigation path, menu, up button, change the back button, navigation drawer"))
        apps.add(App(idApp = 4, nameApp = "Dessert Clicker", descriptorApp = "Lifecycle UI, Timber"))
        apps.add(App(idApp = 5, nameApp = "Guess The Word", descriptorApp = "ViewModel, LiveData"))
        apps.add(App(idApp = 6, nameApp = "Track My Sleep Quality", descriptorApp = "Room Database, RecyclerView"))

        adapter.apps = apps
        recyclerView.adapter = adapter

    }
    override fun onItemClicked(app: App) {
        val id: Int? = app.idApp
        Toast.makeText(this, "click item $id", Toast.LENGTH_LONG).show()
        when (id) {
            0 -> onIntentApp(DiceRollerActivity())
            1 -> onIntentApp(AboutMeActivity())
            2 -> onIntentApp(ColorMyViewsActivity())
            3 -> onIntentApp(AndroidTriviaActivity())
            4 -> onIntentApp(DessertClickerActivity())
            5 -> onIntentApp(GuessTheWordActivity())
            6 -> onIntentApp(TrackMySleepQualityActivity())
        }
    }

    private fun onIntentApp(activity: Activity){
        val intentApp: Intent = Intent(this, activity::class.java)
        intentApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentApp)
    }
}