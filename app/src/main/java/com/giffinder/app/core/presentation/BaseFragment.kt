package com.giffinder.app.core.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.x.closestDI

abstract class BaseFragment<B : ViewDataBinding> : Fragment(), DIAware {

    private val _parentKodein: DI by closestDI()

    override val di: DI = DI.lazy {
        extend(_parentKodein, true)

        with(parentFragment) {
            if (this is BaseFragment<*>) {
                extend(di, true)
            }
        }

        import(viewModelModule, true)
    }

    open val viewModelModule: DI.Module = DI.Module("default2") {}

    override val diTrigger = DITrigger()

    abstract val viewModel: BaseViewModel

    abstract val layoutRes: Int

    private var _binding: B? = null

    protected val binding get() = _binding!!

    override fun onAttach(context: Context) {
        diTrigger.trigger()

        super.onAttach(context)
    }

    abstract fun viewCreated(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        lifecycle.addObserver(viewModel)

        viewCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    inline fun <reified VM : BaseViewModel> vm(factory: ViewModelProvider.Factory): VM {
        return ViewModelProvider(this, factory)[VM::class.java]
    }

    inline fun <reified VM : BaseViewModel> vm(): VM {
        return ViewModelProvider(this)[VM::class.java]
    }
}
