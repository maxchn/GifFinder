package com.giffinder.app.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.giffinder.app.domain.entity.GifData

class GifListAdapter(
    private val onItemClick: (String) -> Unit,
    private val onItemUpdate: (GifData) -> Unit
) : PagingDataAdapter<GifData, GifViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.create(
            parent = parent,
            onItemClick = onItemClick,
            onItemUpdate = onItemUpdate
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(
        holder: GifViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<GifData>() {
        override fun areItemsTheSame(
            oldItem: GifData,
            newItem: GifData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GifData,
            newItem: GifData
        ): Boolean {
            return oldItem == newItem
        }
    }
}
