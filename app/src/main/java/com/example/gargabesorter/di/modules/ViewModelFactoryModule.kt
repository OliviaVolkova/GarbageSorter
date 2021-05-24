package com.example.gargabesorter.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.gargabesorter.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}