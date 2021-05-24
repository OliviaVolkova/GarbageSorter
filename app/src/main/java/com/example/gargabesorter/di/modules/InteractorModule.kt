package com.example.gargabesorter.di.modules

import com.example.gargabesorter.domain.interactors.GameInteractor
import com.example.gargabesorter.domain.interfaces.GameRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun provideGameInteractor(gameRepository: GameRepository): GameInteractor =
        GameInteractor(gameRepository)
}