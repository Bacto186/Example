package com.example.exampleapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDao {
    @Insert
    fun insertApp(app: App)
    @Update
    fun update(app: App)
    @Delete
    fun delete(app: App)
    @Query("SELECT * from exampleapp WHERE id = :id")
    fun getApp(id: Int): App
    @Query("SELECT * FROM exampleapp")
    fun getAllApp(): LiveData<List<App>>
    @Query("DELETE FROM exampleapp")
    fun clear()
    // tra ve mot app dau tien trong list giam dan
    @Query("SELECT * FROM exampleapp ORDER BY id DESC LIMIT 1")
    fun getOneApp(): App
}