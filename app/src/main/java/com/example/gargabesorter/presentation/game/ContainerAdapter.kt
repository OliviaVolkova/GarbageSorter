package com.example.gargabesorter.presentation.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gargabesorter.databinding.ItemContainerBinding
import com.example.gargabesorter.domain.model.Container
import com.squareup.picasso.Picasso

class ContainerAdapter(
    private val itemClick: (String) -> Unit,
    private val picasso: Picasso
) : ListAdapter<Container, ContainerViewHolder>(object : DiffUtil.ItemCallback<Container>() {
    override fun areItemsTheSame(oldItem: Container, newItem: Container): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Container, newItem: Container): Boolean =
        oldItem == newItem

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerViewHolder =
        ContainerViewHolder(
            ItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick,
            picasso
        )

    override fun onBindViewHolder(holder: ContainerViewHolder, position: Int) =
        holder.bind(getItem(position))

}

class ContainerViewHolder(
    private val binding: ItemContainerBinding,
    private val itemClick: (String) -> Unit,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(container: Container) {
        with(binding) {
            root.setOnClickListener {
                itemClick.invoke(container.id)
            }
            picasso.load(container.imageUrl).fit().into(ivContainer)
        }
    }
}
