package com.example.exampleapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [App::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getData(context: Context): AppDatabase {
            synchronized(this){
                var instance:AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"app")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}