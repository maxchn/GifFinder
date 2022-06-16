package com.giffinder.app.presentation.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.giffinder.app.domain.entity.GifData

class GifListAdapter(
    private val onItemBlock: (item: GifData) -> Unit
) : RecyclerView.Adapter<GifViewHolder>() {

    private val diffItemCallback = object : DiffUtil.ItemCallback<GifData>() {
        override fun areItemsTheSame(oldItem: GifData, newItem: GifData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifData, newItem: GifData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffItemCallback)

    private var items: List<GifData>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    fun setNewItems(newItems: List<GifData>) {
        items = newItems
    }

    fun deleteItem(item: GifData) {
        setNewItems(items.filter { it.id != item.id })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.create(
            parent = parent,
            onItemBlock = onItemBlock
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
