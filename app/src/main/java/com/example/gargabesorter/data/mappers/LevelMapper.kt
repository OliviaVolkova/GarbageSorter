package com.example.gargabesorter.data.mappers

import com.example.gargabesorter.data.network.model.LevelRemote
import com.example.gargabesorter.data.room.model.LevelLocal
import com.example.gargabesorter.data.room.model.relations.LevelWithRelations
import com.example.gargabesorter.domain.model.Level

fun levelToLevelLocal(level: Level): LevelLocal =
    LevelLocal(
        id = level.id,
        name = level.name,
        pointsPerItem = level.pointsPerItem,
        itemsCount = level.itemsCount
    )

fun levelWithRelationsToLevel(levelWithRelations: LevelWithRelations): Level =
    Level(
        id = levelWithRelations.level.id,
        name = levelWithRelations.level.name,
        pointsPerItem = levelWithRelations.level.pointsPerItem,
        itemsCount = levelWithRelations.level.itemsCount,
        containers = levelWithRelations.containers.map {
            containerLocalToContainer(it)
        },
        items = levelWithRelations.items.map {
            itemLocalToItem(it)
        }
    )

fun levelRemoteToLevel(level: LevelRemote): Level =
    Level(
        id = level.id,
        name = level.name,
        pointsPerItem = level.pointsPerItem,
        itemsCount = level.itemsCount
    )
