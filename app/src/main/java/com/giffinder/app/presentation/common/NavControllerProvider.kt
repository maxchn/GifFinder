package com.giffinder.app.presentation.common

import androidx.navigation.NavController

class NavControllerProvider {

    companion object {
        private var navController: NavController? = null

        fun setNavController(navController: NavController) {
            this.navController = navController
        }

        fun get(): NavController = navController!!
    }
}
