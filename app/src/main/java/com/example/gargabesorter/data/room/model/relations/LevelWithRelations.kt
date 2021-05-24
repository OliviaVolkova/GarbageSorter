package com.example.gargabesorter.data.room.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gargabesorter.data.room.model.ContainerLocal
import com.example.gargabesorter.data.room.model.ItemLocal
import com.example.gargabesorter.data.room.model.LevelLocal

data class LevelWithRelations(
    @Embedded
    val level: LevelLocal,
    @Relation(parentColumn = "id", entityColumn = "levelId", entity = ItemLocal::class)
    val items: List<ItemLocal>,
    @Relation(parentColumn = "id", entityColumn = "levelId", entity = ContainerLocal::class)
    val containers: List<ContainerLocal>
)