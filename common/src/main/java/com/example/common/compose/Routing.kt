package com.example.common.compose

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import com.google.accompanist.navigation.animation.composable

interface Router {
	fun routeToCompose(screen: String)

	fun roteToFragment(path: String, arguments: Bundle? = null)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.registerInNavHost(
	roteData: ComposablePatchData,
	router: (String, NavOptionsBuilder.() -> Unit) -> Unit
) {
	composable(
		roteData.route,
		roteData.arguments,
		roteData.deepLinks,
		roteData.transitions.popEnterTransition,
		roteData.transitions.popExitTransition,
		roteData.transitions.enterTransition,
		roteData.transitions.exitTransition,
		roteData.content.invoke(router)
	)
}

data class ComposablePatchData(
	val route: String,
	val arguments: List<NamedNavArgument> = emptyList(),
	val deepLinks: List<NavDeepLink> = emptyList(),
	val transitions: Transitions = Transitions(),
	//составная лямбда для того чтобы инкапсулировать роутер в момент инициализации путей
	val content: ((String, NavOptionsBuilder.() -> Unit) -> Unit) -> @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit

) {
	data class Transitions(
		val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
		val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
		val popEnterTransition: (
		AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
		)? = enterTransition,
		val popExitTransition: (
		AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
		)? = exitTransition,
	) {
		companion object {
			val DownTransitions = Transitions(
				enterTransition = {
					when (initialState.destination.route) {
						else -> slideIntoContainer(
							AnimatedContentTransitionScope.SlideDirection.Down,
							animationSpec = tween(1700)
						)
					}
				},
				exitTransition = {
					when (targetState.destination.route) {
						else -> slideOutOfContainer(
							AnimatedContentTransitionScope.SlideDirection.Down,
							animationSpec = tween(1700)
						)
					}
				},
				popEnterTransition = {
					when (initialState.destination.route) {
						else -> slideIntoContainer(
							AnimatedContentTransitionScope.SlideDirection.Down,
							animationSpec = tween(1700)
						)
					}
				},
				popExitTransition = {
					when (targetState.destination.route) {
						else -> slideOutOfContainer(
							AnimatedContentTransitionScope.SlideDirection.Down,
							animationSpec = tween(1700)
						)
					}
				})
		}
	}

}



