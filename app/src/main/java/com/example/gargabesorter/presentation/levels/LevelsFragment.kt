package com.example.gargabesorter.presentation.levels

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gargabesorter.ApplicationDelegate
import com.example.gargabesorter.databinding.FragmentLevelsBinding
import com.example.gargabesorter.domain.interactors.GameInteractor
import com.example.gargabesorter.presentation.router.AppRouter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LevelsFragment : Fragment() {

    private var _binding: FragmentLevelsBinding? = null
    private val binding: FragmentLevelsBinding get() = _binding!!

    private lateinit var adapter: LevelsAdapter

    @Inject
    lateinit var gameInteractor: GameInteractor

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.gameComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLevelsBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LevelsAdapter() {
            itemClicked(it)
        }
        binding.rvLevels.adapter = adapter
        lifecycleScope.launch {
            adapter.submitList(gameInteractor.getLevels().map {
                LevelItem(
                    id = it.id,
                    name = it.name
                )
            })
            binding.progressBar.isVisible = false
        }
    }

    private fun itemClicked(id: String) {
        (activity as AppRouter).openGameFragment(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}