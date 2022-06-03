package com.giffinder.app.presentation.home

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.giffinder.app.R
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.core.presentation.BaseFragment
import com.giffinder.app.databinding.FragmentHomeBinding
import com.giffinder.app.presentation.common.extensions.createAndShowInfoAlertDialog
import com.giffinder.app.presentation.home.adapter.GifListAdapter
import org.kodein.di.instance

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val viewModelModule = HomeModule.get(this)

    override val viewModel: HomeViewModel by instance()

    override val layoutRes: Int = R.layout.fragment_home

    private val networkManager: NetworkManager by instance()

    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        configureUi()

        viewModel.showErrorPopup.observe(viewLifecycleOwner) { message ->
            createAndShowInfoAlertDialog(
                title = getString(R.string.dlg_title_oops),
                message = message
            )
        }
    }

    private fun configureUi() {
        val imagesAdapter = GifListAdapter().apply {
            addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading && this.itemCount < 1) {
                    binding.listImages.isVisible = false
                    binding.textNoData.isVisible = true
                } else {
                    binding.listImages.isVisible = true
                    binding.textNoData.isVisible = false
                }
            }
        }

        binding.listImages.adapter = imagesAdapter

        binding.inputSearch.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getImagesList().observe(viewLifecycleOwner) {
                    imagesAdapter.submitData(lifecycle, it)
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
}
