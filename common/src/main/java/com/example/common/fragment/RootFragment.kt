package com.example.common.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import com.example.common.compose.Router

abstract class RootFragment : Fragment() {

    protected lateinit var navController: NavHostController

    fun composeRouteHandler(screen: String){
        (context as Router).routeTo(screen)
    }
}
