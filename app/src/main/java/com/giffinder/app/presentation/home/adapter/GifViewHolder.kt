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
    private val binding: ItemGifBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GifData) {
        binding.item = item

        Glide
            .with(binding.image)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.ic_placeholder)
            )
            .load(item.images?.downsized?.url)
            .into(binding.image)
    }

    companion object {

        fun create(parent: ViewGroup): GifViewHolder {
            val binding: ItemGifBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gif,
                parent,
                false
            )

            return GifViewHolder(binding)
        }
    }
}
