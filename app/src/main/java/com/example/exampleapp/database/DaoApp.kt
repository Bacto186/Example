package com.example.exampleapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoApp {
    @Insert
    fun insertApp(app: EntityApp)
    @Update
    fun update(app: EntityApp)
    @Query("SELECT * from exampleapp WHERE id = :id")
    fun getApp(id: Int): EntityApp
    @Query("SELECT * FROM exampleapp ORDER BY id DESC")
    fun getAllApp(): LiveData<List<EntityApp>>
    @Query("DELETE FROM exampleapp")
    fun clear()
    // tra ve mot app dau tien trong list giam dan
    @Query("SELECT * FROM exampleapp ORDER BY id DESC LIMIT 1")
    fun getOneApp(): EntityApp
}