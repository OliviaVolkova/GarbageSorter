package com.example.gargabesorter.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelLocal(
    @PrimaryKey
    val id: String,
    val name: String,
    val pointsPerItem: Int,
    val itemsCount: Int
)