package com.giffinder.app.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.giffinder.app.R
import com.giffinder.app.core.presentation.BindingActivity
import com.giffinder.app.databinding.ActivityMainBinding
import com.giffinder.app.presentation.common.NavControllerProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()

        NavControllerProvider.setNavController(findNavController(R.id.main_container))
    }
}
