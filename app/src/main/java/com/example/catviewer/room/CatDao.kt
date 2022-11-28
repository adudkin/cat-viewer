package com.example.catviewer.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CatDao {

    @Query("select * from CatData")
    fun getAll(): LiveData<List<CatData>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: CatData)
}