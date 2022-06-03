package com.giffinder.app.core.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.closestDI
import org.kodein.di.android.retainedDI

abstract class BaseActivity : AppCompatActivity(), DIAware {

    private val _parentKodein by closestDI()

    override val di: DI by retainedDI {
        extend(_parentKodein)
        import(diModule())
    }

    override val diTrigger = DITrigger()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        diTrigger.trigger()
        super.onCreate(savedInstanceState)
    }

    abstract fun diModule(): DI.Module

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}
