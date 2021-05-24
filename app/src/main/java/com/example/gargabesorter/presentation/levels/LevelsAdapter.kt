package com.example.gargabesorter.presentation.levels

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gargabesorter.databinding.ItemLevelBinding

class LevelsAdapter(
    private val itemClick: (String) -> Unit
) : ListAdapter<LevelItem, LevelViewHolder>(object : DiffUtil.ItemCallback<LevelItem>() {
    override fun areItemsTheSame(oldItem: LevelItem, newItem: LevelItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LevelItem, newItem: LevelItem): Boolean =
        oldItem == newItem

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder =
        LevelViewHolder(
            ItemLevelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick
        )

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class LevelViewHolder(
    private val binding: ItemLevelBinding,
    private val itemClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(level: LevelItem) {
        binding.tvName.text = level.name
        binding.root.setOnClickListener {
            itemClick.invoke(level.id)
        }
    }
}