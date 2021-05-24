package com.example.gargabesorter.di.modules

import android.content.Context
import androidx.room.Room
import com.example.gargabesorter.data.room.DbInstance
import com.example.gargabesorter.data.room.daos.GameDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDbInstance(context: Context): DbInstance = Room.databaseBuilder(
        context,
        DbInstance::class.java,
        "garbageDb"
    ).build()

    @Singleton
    @Provides
    fun provideDao(dbInstance: DbInstance): GameDao = dbInstance.gameDao
}