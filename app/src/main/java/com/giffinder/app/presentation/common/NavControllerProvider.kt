package com.giffinder.app.presentation.common

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.giffinder.app.R

class NavControllerProvider(private val activity: FragmentActivity) {
    fun get(): NavController = activity.findNavController(R.id.main_container)
}
