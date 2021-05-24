package com.example.gargabesorter.data.network.firebase

import com.example.gargabesorter.data.mappers.containerRemoteToContainer
import com.example.gargabesorter.data.mappers.itemRemoteToItem
import com.example.gargabesorter.data.mappers.levelRemoteToLevel
import com.example.gargabesorter.data.network.model.ContainerRemote
import com.example.gargabesorter.data.network.model.ItemRemote
import com.example.gargabesorter.data.network.model.LevelRemote
import com.example.gargabesorter.domain.model.Item
import com.example.gargabesorter.domain.model.Level
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreApi(
    private val firestore: FirebaseFirestore
) : FirebaseApi {

    private val collectionRef = firestore.collection("levels")

    override suspend fun getLevels(): List<Level> {
        return collectionRef.get().await().documents.map { documentSnapshot ->
            val level = levelRemoteToLevel(
                documentSnapshot.toObject(LevelRemote::class.java)
                    ?: throw IllegalStateException("Unchecked cast")
            )
            val items = mutableListOf<Item>()
            level.containers =
                documentSnapshot.reference.collection("container").get().await().documents.map {
                    val container = containerRemoteToContainer(
                        it.toObject(ContainerRemote::class.java)
                            ?: throw IllegalStateException("Unchecked cast")
                    )
                    items.addAll(it.reference.collection("items").get().await().documents.map {
                        itemRemoteToItem(
                            it.toObject(ItemRemote::class.java)
                                ?: throw IllegalStateException("Unchecked cast"),
                            container.id
                        )
                    })
                    container
                }
            return@map level
        }
    }

    override suspend fun getLevelById(levelId: String): Level? {
        return collectionRef.whereEqualTo("id", levelId).get()
            .await().documents.map { documentSnapshot ->
            val level = levelRemoteToLevel(
                documentSnapshot.toObject(LevelRemote::class.java)
                    ?: throw IllegalStateException("Unchecked cast")
            )
            val items = mutableListOf<Item>()
            level.containers =
                documentSnapshot.reference.collection("container").get()
                    .await().documents.map { snapshot ->
                        val container = containerRemoteToContainer(
                            snapshot.toObject(ContainerRemote::class.java)
                                ?: throw IllegalStateException("Unchecked cast")
                        )
                        items.addAll(
                            snapshot.reference.collection("items").get().await().documents.map {
                                itemRemoteToItem(
                                    it.toObject(ItemRemote::class.java)
                                        ?: throw IllegalStateException("Unchecked cast"),
                                    container.id
                                )
                            })
                        container
                    }
            level.items = items
            return@map level
        }.firstOrNull()
    }
}