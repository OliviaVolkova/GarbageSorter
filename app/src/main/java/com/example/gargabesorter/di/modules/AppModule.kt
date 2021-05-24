package com.example.gargabesorter.di.modules

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Named("IO")
    @Provides
    fun provideCoroutineContext(): CoroutineContext = SupervisorJob() + Dispatchers.IO

    @Provides
    fun providePicasso(): Picasso = Picasso.get()
}