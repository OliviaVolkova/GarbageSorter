package com.example.gargabesorter.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.gargabesorter.data.mappers.containerToContainerLocal
import com.example.gargabesorter.data.mappers.itemToLocalItem
import com.example.gargabesorter.data.mappers.levelToLevelLocal
import com.example.gargabesorter.data.mappers.levelWithRelationsToLevel
import com.example.gargabesorter.data.network.firebase.FirebaseApi
import com.example.gargabesorter.data.room.daos.GameDao
import com.example.gargabesorter.data.room.model.Point
import com.example.gargabesorter.domain.interfaces.GameRepository
import com.example.gargabesorter.domain.model.Level

class FirebaseGameRepository(
    private val firebaseApi: FirebaseApi,
    private val gameDao: GameDao
) : GameRepository {

    override suspend fun getLevels(): List<Level> {
        return try {
            firebaseApi.getLevels().also { list ->
                gameDao.insertGames(
                    list.map {
                        levelToLevelLocal(it)
                    },
                    list.flatMap { level ->
                        level.containers.map { container ->
                            containerToContainerLocal(container, level.id)
                        }
                    },
                    list.flatMap { level ->
                        level.items.map { item ->
                            itemToLocalItem(item, level.id)
                        }
                    }
                )
            }
        } catch (e: Exception) {
            gameDao.getLevels().map {
                levelWithRelationsToLevel(it)
            }
        }

    }

    override suspend fun getLevelById(gameId: String): Level? {
        return try {
            firebaseApi.getLevelById(gameId)
        } catch (e: Exception) {
            levelWithRelationsToLevel(
                gameDao.getLevelById(gameId)
                    ?: throw IllegalArgumentException("Such level not found")
            )
        }
    }

    override suspend fun addPoints(points: Long) = gameDao.addPoints(
        Point(points = points)
    )

    override fun getPoints(): LiveData<Long> = gameDao.getPoints().map {
        it ?: 0L
    }

}