package com.example.gargabesorter.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gargabesorter.data.room.model.ContainerLocal
import com.example.gargabesorter.data.room.model.ItemLocal
import com.example.gargabesorter.data.room.model.LevelLocal
import com.example.gargabesorter.data.room.model.Point
import com.example.gargabesorter.data.room.model.relations.LevelWithRelations

@Dao
interface GameDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(
        levels: List<LevelLocal>,
        containers: List<ContainerLocal>,
        items: List<ItemLocal>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPoints(point: Point)

    @Query("SELECT SUM(points) FROM POINT ")
    fun getPoints(): LiveData<Long?>


    @Query("SELECT * FROM LevelLocal")
    suspend fun getLevels(): List<LevelWithRelations>

    @Query("SELECT * FROM LevelLocal WHERE id = :gameId")
    suspend fun getLevelById(gameId: String): LevelWithRelations?
}