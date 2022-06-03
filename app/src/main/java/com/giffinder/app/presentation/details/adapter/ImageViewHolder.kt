package com.giffinder.app.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.giffinder.app.R
import com.giffinder.app.databinding.ItemGifDetailsBinding
import com.giffinder.app.domain.entity.GifData

class ImageViewHolder(
    private val binding: ItemGifDetailsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GifData) {
        with(binding) {
            this.item = item

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

        fun create(parent: ViewGroup): ImageViewHolder {
            val binding: ItemGifDetailsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gif_details,
                parent,
                false
            )

            return ImageViewHolder(binding = binding)
        }
    }
}
