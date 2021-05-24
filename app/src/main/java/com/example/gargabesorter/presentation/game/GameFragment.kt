package com.example.gargabesorter.presentation.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gargabesorter.ApplicationDelegate
import com.example.gargabesorter.databinding.FragmentGameBinding
import com.example.gargabesorter.presentation.router.AppRouter
import com.example.gargabesorter.utils.ViewModelProviderFactory
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.squareup.picasso.Picasso
import javax.inject.Inject

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding get() = _binding!!

    private lateinit var adapter: ContainerAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var picasso: Picasso

    private lateinit var viewModel: GameViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ApplicationDelegate.gameComponent.create().inject(this)
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initClickListeners()
        initLiveDataListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            btnStart.setOnClickListener {
                startClick()
            }
            btnBack.setOnClickListener {
                (activity as AppRouter).navigateUp()
            }
            btnFinish.setOnClickListener {
                viewModel.finishGame()
            }
        }
    }

    private fun initLiveDataListeners() {
        with(viewModel) {
            getContainers().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            getItem().observe(viewLifecycleOwner) {
                Picasso.get().load(it.imageUrl).fit().into(binding.ivItem)
            }
            isStarted().observe(viewLifecycleOwner) {
                if (it) {
                    setGameState()
                } else {
                    setStartState()
                }
            }
            itemsCount().observe(viewLifecycleOwner) { pair ->
                "${pair.first}/${pair.second}".also { binding.tvItemsCount.text = it }
            }
            points().observe(viewLifecycleOwner) {
                binding.tvPoints.text = "$it"
            }
        }
    }

    private fun startClick() {
        setGameState()
        val id = arguments?.let {
            GameFragmentArgs.fromBundle(it).difficultId
        } ?: throw IllegalArgumentException("Id not found")
        viewModel.start(id)
    }

    private fun setGameState() {
        with(binding) {
            rvContainers.visibility = View.VISIBLE
            btnStart.visibility = View.INVISIBLE
            cvItem.visibility = View.VISIBLE
            tvItemsCount.visibility = View.VISIBLE
            btnBack.visibility = View.INVISIBLE
            btnFinish.visibility = View.VISIBLE
            tvPoints.visibility = View.VISIBLE
        }
    }

    private fun setStartState() {
        with(binding) {
            rvContainers.visibility = View.INVISIBLE
            btnStart.visibility = View.VISIBLE
            cvItem.visibility = View.INVISIBLE
            tvItemsCount.visibility = View.INVISIBLE
            btnBack.visibility = View.VISIBLE
            btnFinish.visibility = View.INVISIBLE
            tvPoints.visibility = View.INVISIBLE
        }
    }

    private fun initRecycler() {
        adapter = ContainerAdapter(
            itemClick = {
                viewModel.containerClicked(it)
            },
            picasso = picasso
        )
        binding.rvContainers.adapter = adapter
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.justifyContent = JustifyContent.CENTER
        layoutManager.alignItems = AlignItems.CENTER
        binding.rvContainers.layoutManager = layoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}