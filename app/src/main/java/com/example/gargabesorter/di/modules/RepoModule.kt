package com.example.gargabesorter.di.modules

import com.example.gargabesorter.data.network.firebase.FirebaseApi
import com.example.gargabesorter.data.repositories.FirebaseGameRepository
import com.example.gargabesorter.data.room.daos.GameDao
import com.example.gargabesorter.domain.interfaces.GameRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideGameRepository(
        firebaseApi: FirebaseApi,
        gameDao: GameDao
    ): GameRepository = FirebaseGameRepository(firebaseApi, gameDao)
}