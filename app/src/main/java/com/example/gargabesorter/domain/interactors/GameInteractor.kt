package com.example.gargabesorter.domain.interactors

import androidx.lifecycle.LiveData
import com.example.gargabesorter.domain.interfaces.GameRepository
import com.example.gargabesorter.domain.model.Level

class GameInteractor(
    private val gameRepository: GameRepository
) {
    suspend fun getLevels(): List<Level> = gameRepository.getLevels().sortedBy {
        it.pointsPerItem
    }

    suspend fun getLevelById(levelId: String): Level? = gameRepository.getLevelById(levelId)

    suspend fun addPoints(points: Long) = gameRepository.addPoints(points)

    fun getPoints(): LiveData<Long> = gameRepository.getPoints()
}