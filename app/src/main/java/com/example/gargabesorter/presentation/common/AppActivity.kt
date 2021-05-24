package com.example.gargabesorter.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.gargabesorter.ApplicationDelegate
import com.example.gargabesorter.R
import com.example.gargabesorter.databinding.ActivityMainBinding
import com.example.gargabesorter.presentation.levels.LevelsFragmentDirections
import com.example.gargabesorter.presentation.router.AppRouter
import com.example.gargabesorter.utils.ViewModelProviderFactory
import javax.inject.Inject

class AppActivity : AppCompatActivity(), AppRouter {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private lateinit var viewModel: AppViewModel

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ApplicationDelegate.gameComponent.create().inject(this)
        controller = findNavController(R.id.hostFragment)
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(AppViewModel::class.java)
        initLiveDataListeners()
    }

    private fun initLiveDataListeners() {
        viewModel.getPoints().observe(this) {
            binding.tvPoints.text = "$it"
        }
    }

    override fun openLevelsFragment() {
        controller.navigate(R.id.diffucultFragment)
    }

    override fun openGameFragment(difficultId: String) {
        val action = LevelsFragmentDirections.actionDiffucultFragmentToGameFragment(difficultId)
        controller.navigate(action)
    }

    override fun navigateUp() {
        controller.navigateUp()
    }

}