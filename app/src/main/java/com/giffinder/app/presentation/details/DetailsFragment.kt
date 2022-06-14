package com.giffinder.app.presentation.details

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.giffinder.app.R
import com.giffinder.app.core.presentation.BaseFragment
import com.giffinder.app.databinding.FragmentDetailsBinding
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.presentation.common.dialog.AlertDialogConfig
import com.giffinder.app.presentation.common.extensions.createAndShowQuestionDialog
import com.giffinder.app.presentation.details.adapter.ImagesAdapter
import org.kodein.di.instance
import java.io.File

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val viewModelModule = DetailsModule.get(this)

    override val viewModel: DetailsViewModel by instance()

    override val layoutRes: Int = R.layout.fragment_details

    private val args: DetailsFragmentArgs by navArgs()

    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        val adapter = ImagesAdapter(onItemBlock = ::onBlockGif).apply {
            setNewItems(args.items.toList())
        }

        binding.viewPagerImages.adapter = adapter
        binding.viewPagerImages.isSaveEnabled = false
        binding.viewPagerImages.currentItem = args.selectedItemIndex

        viewModel.blockGifFile.observe(viewLifecycleOwner) { gif ->
            val file = File(gif.localUrl)

            if (file.exists()) {
                file.delete()
            }

            adapter.deleteItem(gif)
        }
    }

    private fun onBlockGif(item: GifData) {
        createAndShowQuestionDialog(
            AlertDialogConfig(
                message = getString(R.string.dlg_block_gif),
                positiveButtonTextRes = R.string.action_block,
                onClickPositiveButtonListener = { _, _ ->
                    viewModel.onClickBlockGif(item)
                }
            )
        )
    }
}
