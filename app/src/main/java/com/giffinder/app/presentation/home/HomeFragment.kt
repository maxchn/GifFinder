package com.giffinder.app.presentation.home

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.giffinder.app.R
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.core.presentation.BaseFragment
import com.giffinder.app.core.presentation.EventBus
import com.giffinder.app.databinding.FragmentHomeBinding
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.presentation.common.Constants
import com.giffinder.app.presentation.common.extensions.createAndShowInfoAlertDialog
import com.giffinder.app.presentation.home.adapter.GifListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import org.kodein.di.instance

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val viewModelModule = HomeModule.get(this)

    override val viewModel: HomeViewModel by instance()

    override val layoutRes: Int = R.layout.fragment_home

    private val networkManager: NetworkManager by instance()

    private val eventBus: EventBus by instance()

    private var gifListAdapter: GifListAdapter? = null
    private var lastVisiblePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gifListAdapter = GifListAdapter(
            onItemClick = ::onClickByGif,
            onItemUpdate = viewModel::updateGif
        ).apply {
            addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading && this.itemCount < 1) {
                    hideListAndShowPlaceholder()
                } else {
                    showListAndHidePlaceholder()
                }
            }
        }
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        configureUi()

        viewModel.showErrorPopup.observe(viewLifecycleOwner) { message ->
            createAndShowInfoAlertDialog(
                title = getString(R.string.dlg_title_oops),
                message = message
            )
        }

        lifecycleScope.launchWhenStarted {

            eventBus.events.filter { event -> event.type == EventBus.AppEventType.REMOVE_GIF }
                .collectLatest { event ->
                    event.options.getParcelable<GifData>(Constants.ARG_GIF_DATA)?.let {
                        viewModel.getImagesList().observe(viewLifecycleOwner) {
                            gifListAdapter?.submitData(lifecycle, it)
                        }
                    }
                }
        }
    }

    override fun onResume() {
        super.onResume()

        if (lastVisiblePosition != -1) {
            showListAndHidePlaceholder()

            (binding.listImages.layoutManager as GridLayoutManager).scrollToPosition(
                lastVisiblePosition
            )

            lastVisiblePosition = -1
        }
    }

    override fun onPause() {
        super.onPause()

        lastVisiblePosition = (binding.listImages.layoutManager as GridLayoutManager)
            .findFirstVisibleItemPosition()
    }

    override fun onStop() {
        super.onStop()

        binding.listImages.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()

        gifListAdapter = null
    }

    private fun configureUi() {
        binding.listImages.adapter = gifListAdapter
        binding.inputSearch.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getImagesList().observe(viewLifecycleOwner) {
                    gifListAdapter?.submitData(lifecycle, it)
                }

                true
            } else {
                false
            }
        }

        networkManager.processing.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
    }

    private fun showListAndHidePlaceholder() {
        binding.listImages.isVisible = true
        binding.textNoData.isVisible = false
    }

    private fun hideListAndShowPlaceholder() {
        binding.listImages.isVisible = false
        binding.textNoData.isVisible = true
    }

    private fun onClickByGif(idSelectedItem: String) {
        viewModel.navigateToDetailsScreen(
            items = (binding.listImages.adapter as GifListAdapter).snapshot().items,
            idSelectedItem = idSelectedItem
        )
    }
}
