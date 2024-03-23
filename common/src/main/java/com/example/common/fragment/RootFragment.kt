package com.example.common.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

abstract class RootFragment : Fragment() {

    protected lateinit var navController: NavHostController

    fun composeRouteHandler(screen: String, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (navController.currentDestination?.route == screen) return
        navController.navigate(screen) {
            //builder()
            popUpTo(screen){
                inclusive=true
            }
        }
    }
}
