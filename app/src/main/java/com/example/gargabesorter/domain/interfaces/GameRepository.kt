package com.example.gargabesorter.domain.interfaces

import androidx.lifecycle.LiveData
import com.example.gargabesorter.domain.model.Level

interface GameRepository {
    suspend fun getLevels(): List<Level>

    suspend fun getLevelById(gameId: String): Level?

    suspend fun addPoints(points: Long)

    fun getPoints(): LiveData<Long>
}