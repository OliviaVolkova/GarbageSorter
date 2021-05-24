package com.example.gargabesorter.data.network.firebase

import com.example.gargabesorter.domain.model.Level

interface FirebaseApi {

    suspend fun getLevels(): List<Level>

    suspend fun getLevelById(levelId: String): Level?
}