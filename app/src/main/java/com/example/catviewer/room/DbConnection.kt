package com.example.catviewer.room


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatData::class], version = 1, exportSchema = false)
abstract class DbConnection : RoomDatabase() {
    abstract fun entityDao(): CatDao
}