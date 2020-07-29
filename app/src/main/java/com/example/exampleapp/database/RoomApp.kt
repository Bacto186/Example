package com.example.exampleapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityApp::class], version = 1, exportSchema = false)
abstract class RoomApp : RoomDatabase() {
    abstract val dao: DaoApp
    companion object {
        @Volatile
        private var INSTANCE: RoomApp? = null

        fun getData(context: Context): RoomApp {
            synchronized(this){
                var instance:RoomApp? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,RoomApp::class.java,"app")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}