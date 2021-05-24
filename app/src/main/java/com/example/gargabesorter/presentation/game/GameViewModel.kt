package com.example.gargabesorter.presentation.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gargabesorter.domain.interactors.GameInteractor
import com.example.gargabesorter.domain.model.Container
import com.example.gargabesorter.domain.model.Item
import com.example.gargabesorter.domain.model.Level
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class GameViewModel @Inject constructor(
    private val gameInteractor: GameInteractor,
    @Named("IO") private val coroutineContext: CoroutineContext,
) : ViewModel() {

    private val containers: MutableLiveData<List<Container>> = MutableLiveData()
    private val item: MutableLiveData<Item> = MutableLiveData()
    private lateinit var level: Level
    private var points: MutableLiveData<Long> = MutableLiveData(0L)
    private lateinit var items: MutableList<Item>
    private val isStarted: MutableLiveData<Boolean> = MutableLiveData(false)
    private val itemsCompletedCount: MutableLiveData<Pair<Int, Int>> = MutableLiveData()
    private var itemsCompleted: Int = 0

    fun start(id: String) {
        items = mutableListOf()
        itemsCompleted = 0
        viewModelScope.launch(coroutineContext) {
            level = gameInteractor.getLevelById(id)
                ?: throw IllegalArgumentException("Level id not found")
            containers.postValue(level.containers)
            items = level.items.toMutableList()
            nextItem()
        }
        isStarted.value = true
    }

    fun points(): LiveData<Long> = points

    fun isStarted(): LiveData<Boolean> = isStarted


    fun getContainers(): LiveData<List<Container>> = containers

    fun getItem(): LiveData<Item> = item

    fun itemsCount(): LiveData<Pair<Int, Int>> = itemsCompletedCount

    private fun nextItem() {
        itemsCompletedCount.postValue(Pair(itemsCompleted, level.itemsCount))
        val localItem = items.random().also {
            items.remove(it)
        }
        Log.d("Olivia", "${localItem.imageUrl}")
        item.postValue(localItem)
        if (itemsCompleted == level.itemsCount) finishGame()
    }

    fun finishGame() {
        savePoints(points.value ?: 0L)
        points.postValue(0L)
        isStarted.postValue(false)
    }

    private fun savePoints(points: Long) {
        viewModelScope.launch(coroutineContext) {
            gameInteractor.addPoints(
                points
            )
        }
    }

    fun containerClicked(containerId: String) {
        if (containerId == item.value?.containerId) {
            points.postValue((points.value ?: 0L) + level.pointsPerItem)
        }
        itemsCompleted++
        nextItem()
    }

}