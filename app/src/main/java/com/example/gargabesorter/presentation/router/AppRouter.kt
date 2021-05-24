package com.example.gargabesorter.presentation.router

interface AppRouter {
    fun openLevelsFragment()

    fun openGameFragment(difficultId: String)

    fun navigateUp()
}