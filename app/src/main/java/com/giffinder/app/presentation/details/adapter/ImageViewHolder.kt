package com.giffinder.app.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

            val url = item.localUrl.ifEmpty {
                item.remoteUrl
            }

            Glide
                .with(image)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(R.drawable.ic_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .load(url)
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
