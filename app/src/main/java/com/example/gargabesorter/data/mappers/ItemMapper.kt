package com.example.gargabesorter.data.mappers

import com.example.gargabesorter.data.network.model.ItemRemote
import com.example.gargabesorter.data.room.model.ItemLocal
import com.example.gargabesorter.domain.model.Item

fun itemLocalToItem(itemLocal: ItemLocal): Item =
    Item(
        id = itemLocal.id,
        name = itemLocal.name,
        imageUrl = itemLocal.imageUrl,
        containerId = itemLocal.containerId
    )

fun itemToLocalItem(item: Item, levelId: String): ItemLocal =
    ItemLocal(
        id = item.id,
        name = item.name,
        imageUrl = item.imageUrl,
        containerId = item.containerId,
        levelId = levelId
    )

fun itemRemoteToItem(item: ItemRemote, containerId: String): Item =
    Item(
        id = item.id,
        name = item.name,
        imageUrl = item.imageUrl,
        containerId = containerId
    )