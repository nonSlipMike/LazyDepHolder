package com.example.composeroot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.common.compose.ComposablePatchData
import com.example.common.compose.Router
import com.example.common.compose.registerInNavHost
import com.example.common.compose.setContent
import com.example.common.fragment.RootFragment
import com.example.di.ComposeRootComponent
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import javax.inject.Inject

class ComposeRootFragment : RootFragment() {


	@Inject
	lateinit var routes: Map<String, ComposablePatchData>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		ComposeRootComponent.getInstance().inject(this)
	}

	@OptIn(ExperimentalAnimationApi::class)
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	) = setContent {
		MaterialTheme {
			Column(modifier = Modifier.fillMaxSize()) {
				navController = rememberAnimatedNavController()
				AnimatedNavHost(
					navController = navController,
					startDestination = "homescreen",
					modifier = Modifier.weight(1f)
				) {
					// инжект модулей с помошью Dagger @InToMap из Feature модуля
					routes.forEach { registerInNavHost(it.value, ::composeRouteHandler) }

					composable("orders") { OrdersScreen(::composeRouteHandler) }

					composable("homescreen") { HomeScreen() }

					composable(
						"details?{argument}",
						arguments = listOf(navArgument("argument") {
							type = NavType.StringType
						}),
						deepLinks = listOf(navDeepLink {
							uriPattern = "https://vvx.com?{argument}"
						}),
					) { backStackEntry ->
						val article = backStackEntry.arguments?.getString("argument")
						OrdersScreen(::composeRouteHandler, "Showing $article")
					}
				}

				BottomMenu(::composeRouteHandler)
			}
		}
	}

	companion object {
		fun getInstance() = ComposeRootFragment()
	}
}

@Composable
fun OrdersScreen(
	routeHandler: (String, NavOptionsBuilder.() -> Unit) -> Unit,
	argument: String? = null,
) {
	Column() {
		argument?.let {
			Text(text = it, modifier = Modifier.padding(40.dp))
		}
		Text(
			"тыкни сюда чтобы вернуться на главную Хоста Compose ",
			modifier = Modifier
				.padding(40.dp)
				.clickable { routeHandler("homescreen") {} })
	}

}

@Composable
fun HomeScreen(
) {
	val router = (LocalContext.current as Router)
	Text(
		text = "Тыкни на надпись чтобы перейти на фраrмент A_Feature",
		modifier = Modifier.clickable {
			//router.routeWithDeepLink("android-app://example.myapp.app/a_feature_fragment")
			router.roteToFragment("feature/AFeatureFragment")
		})
}
