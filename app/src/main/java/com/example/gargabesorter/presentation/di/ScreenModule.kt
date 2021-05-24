package com.example.gargabesorter.presentation.di

import androidx.lifecycle.ViewModel
import com.example.gargabesorter.di.ViewModelKey
import com.example.gargabesorter.presentation.common.AppViewModel
import com.example.gargabesorter.presentation.game.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun bindGameViewModule(viewModel: GameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    fun bindAppViewModule(viewModel: AppViewModel): ViewModel

}