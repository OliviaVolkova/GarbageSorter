package com.example.gargabesorter.domain.model

data class Item(
    val id: String,
    val name: String,
    val imageUrl: String,
    val containerId: String
)