package com.example.gargabesorter.presentation.common

import androidx.lifecycle.ViewModel
import com.example.gargabesorter.domain.interactors.GameInteractor
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val gameInteractor: GameInteractor
) : ViewModel() {

    fun getPoints() = gameInteractor.getPoints()

}