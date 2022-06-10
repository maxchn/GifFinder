package com.giffinder.app.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.giffinder.app.R
import com.giffinder.app.databinding.ItemGifBinding
import com.giffinder.app.domain.entity.GifData
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

class GifViewHolder(
    private val binding: ItemGifBinding,
    private val onItemClick: (String) -> Unit,
    private val onItemUpdate: (GifData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GifData) {
        with(binding) {
            root.setOnClickListener {
                onItemClick.invoke(item.id)
            }

            val url = item.localUrl.ifEmpty {
                item.remoteUrl
            }

            Glide
                .with(image)
                .asGif()
                .load(url)
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.ic_placeholder)
                )
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean = false

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.let { image -> saveGif(item, image) }

                        return false
                    }
                })
                .into(image)
        }
    }

    private fun saveGif(item: GifData, image: GifDrawable) {
        if (item.localUrl.isNotEmpty()) {
            return
        }

        val imageFileName = "${item.id}.gif"
        val storageDir = binding.root.context.filesDir

        val imageFile = File(storageDir, imageFileName)
        val savedImagePath: String = imageFile.absolutePath

        FileOutputStream(savedImagePath).use {
            val bytes = ByteArray(image.buffer.capacity())
            (image.buffer.duplicate().clear() as ByteBuffer).get(bytes)

            it.write(bytes, 0, bytes.size)
        }

        onItemUpdate.invoke(item.copy(localUrl = savedImagePath))
    }

    companion object {

        fun create(
            parent: ViewGroup,
            onItemClick: (String) -> Unit,
            onItemUpdate: (GifData) -> Unit
        ): GifViewHolder {
            val binding: ItemGifBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gif,
                parent,
                false
            )

            return GifViewHolder(
                binding = binding,
                onItemClick = onItemClick,
                onItemUpdate = onItemUpdate
            )
        }
    }
}
