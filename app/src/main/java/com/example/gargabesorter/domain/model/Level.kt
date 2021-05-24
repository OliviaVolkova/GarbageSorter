package com.example.gargabesorter.domain.model

data class Level(
    val id: String,
    val name: String,
    val pointsPerItem: Int,
    val itemsCount: Int,
    var containers: List<Container> = listOf(),
    var items: List<Item> = listOf()
)