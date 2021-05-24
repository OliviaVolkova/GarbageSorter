package com.example.gargabesorter.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LevelLocal::class,
            parentColumns = ["id"],
            childColumns = ["levelId"]
        )
    ]
)
data class ItemLocal(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val levelId: String,
    val containerId: String
)