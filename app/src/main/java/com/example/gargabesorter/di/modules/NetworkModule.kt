package com.example.gargabesorter.di.modules

import com.example.gargabesorter.data.network.firebase.FirebaseApi
import com.example.gargabesorter.data.network.firebase.FirestoreApi
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideFirebase(firestore: FirebaseFirestore): FirebaseApi = FirestoreApi(firestore)
}
