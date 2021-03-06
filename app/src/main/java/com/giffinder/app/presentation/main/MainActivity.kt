package com.giffinder.app.presentation.main

import android.os.Bundle
import com.giffinder.app.R
import com.giffinder.app.core.presentation.BindingActivity
import com.giffinder.app.databinding.ActivityMainBinding
import com.giffinder.app.presentation.common.NavControllerProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun diModule() = DI.Module(MainActivity::class.java.toString()) {
        bind<NavControllerProvider>() with singleton { NavControllerProvider(this@MainActivity) }

        bind<MainViewModel>() with provider { MainViewModel() }
    }

    override val viewModel: MainViewModel by instance()

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }
}
