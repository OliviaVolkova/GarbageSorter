package com.example.gargabesorter.di.components

import android.content.Context
import com.example.gargabesorter.di.modules.*
import com.example.gargabesorter.presentation.di.ScreenComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DBModule::class,
        NetworkModule::class,
        RepoModule::class,
        InteractorModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    fun screenComponent(): ScreenComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent

    }
}