package com.giffinder.app.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<B : ViewDataBinding> : BaseActivity() {

    private var _binding: B? = null

    protected val binding get() = _binding!!

    abstract val viewModel: BaseViewModel

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutRes(), null, false)
        binding.lifecycleOwner = this

        lifecycle.addObserver(viewModel)

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}
