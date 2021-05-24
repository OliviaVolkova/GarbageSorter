package com.example.gargabesorter.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gargabesorter.data.room.daos.GameDao
import com.example.gargabesorter.data.room.model.ContainerLocal
import com.example.gargabesorter.data.room.model.ItemLocal
import com.example.gargabesorter.data.room.model.LevelLocal
import com.example.gargabesorter.data.room.model.Point

@Database(
    entities = [
        ContainerLocal::class,
        LevelLocal::class,
        ItemLocal::class,
        Point::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DbInstance : RoomDatabase() {
    abstract val gameDao: GameDao
}