package com.giffinder.app.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.giffinder.app.R
import com.giffinder.app.databinding.ItemGifBinding
import com.giffinder.app.domain.entity.GifData

class GifViewHolder(
    private val binding: ItemGifBinding,
    private val onItemClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GifData) {
        with(binding) {
            this.item = item

            root.setOnClickListener {
                onItemClick.invoke(item.id)
            }

            Glide
                .with(image)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(R.drawable.ic_placeholder)
                )
                .load(item.images?.downsized?.url)
                .into(image)
        }
    }

    companion object {

        fun create(parent: ViewGroup, onItemClick: (String) -> Unit): GifViewHolder {
            val binding: ItemGifBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gif,
                parent,
                false
            )

            return GifViewHolder(binding = binding, onItemClick = onItemClick)
        }
    }
}