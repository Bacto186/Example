package com.example.exampleapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExampleApp")
data class App(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_app")
    val nameApp: String? = "",
    @ColumnInfo(name = "description_app")
    val descriptionApp: String? = ""
)