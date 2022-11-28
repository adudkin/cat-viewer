package com.example.catviewer.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var url: String
)