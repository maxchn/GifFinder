package com.giffinder.app.presentation.details

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.giffinder.app.R
import com.giffinder.app.core.presentation.BaseFragment
import com.giffinder.app.core.presentation.EventBus
import com.giffinder.app.databinding.FragmentDetailsBinding
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.presentation.common.Constants.Companion.ARG_GIF_DATA
import com.giffinder.app.presentation.common.dialog.AlertDialogConfig
import com.giffinder.app.presentation.common.extensions.createAndShowQuestionDialog
import com.giffinder.app.presentation.details.adapter.GifListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import org.kodein.di.instance
import java.io.File

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override val viewModelModule = DetailsModule.get(this)

    override val viewModel: DetailsViewModel by instance()

    override val layoutRes: Int = R.layout.fragment_details

    private val args: DetailsFragmentArgs by navArgs()

    private val eventBus: EventBus by instance()

    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        val adapter = GifListAdapter(onItemBlock = ::onBlockGif).apply {
            setNewItems(args.items.toList())
        }

        binding.viewPagerImages.adapter = adapter
        binding.viewPagerImages.isSaveEnabled = false
        binding.viewPagerImages.setCurrentItem(args.selectedItemIndex, false)

        lifecycleScope.launchWhenCreated {
            eventBus.events.filter { event -> event.type == EventBus.AppEventType.REMOVE_GIF }
                .collectLatest {
                    it.options.getParcelable<GifData>(ARG_GIF_DATA)?.let { gif ->
                        val file = File(gif.localUrl)

                        if (file.exists()) {
                            file.delete()
                        }

                        adapter.deleteItem(gif)
                    }
                }
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
