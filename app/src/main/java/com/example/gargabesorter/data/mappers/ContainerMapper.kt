package com.example.gargabesorter.data.mappers

import com.example.gargabesorter.data.network.model.ContainerRemote
import com.example.gargabesorter.data.room.model.ContainerLocal
import com.example.gargabesorter.domain.model.Container

fun containerToContainerLocal(
    container: Container,
    levelId: String
): ContainerLocal = ContainerLocal(
    id = container.id,
    imageUrl = container.imageUrl,
    name = container.name,
    levelId = levelId
)

fun containerLocalToContainer(
    containerLocal: ContainerLocal
): Container = Container(
    id = containerLocal.id,
    imageUrl = containerLocal.imageUrl,
    name = containerLocal.name,
)

fun containerRemoteToContainer(container: ContainerRemote): Container =
    Container(
        id = container.id,
        imageUrl = container.imageUrl,
        name = container.name
    )
