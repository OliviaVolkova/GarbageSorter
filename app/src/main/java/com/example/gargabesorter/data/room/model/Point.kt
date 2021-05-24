package com.example.gargabesorter.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Point(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val points: Long
)