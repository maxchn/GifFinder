package com.giffinder.app.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.giffinder.app.domain.entity.GifData

class GifListAdapter : PagingDataAdapter<GifData, GifViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<GifData>() {
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
