package com.example.gargabesorter.presentation.di

import com.example.gargabesorter.di.ScreenScope
import com.example.gargabesorter.presentation.common.AppActivity
import com.example.gargabesorter.presentation.game.GameFragment
import com.example.gargabesorter.presentation.levels.LevelsFragment
import dagger.Subcomponent

@Subcomponent(modules = [ScreenModule::class])
@ScreenScope
interface ScreenComponent {

    fun inject(gameFragment: GameFragment)

    fun inject(levelsFragment: LevelsFragment)

    fun inject(activity: AppActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ScreenComponent
    }
}