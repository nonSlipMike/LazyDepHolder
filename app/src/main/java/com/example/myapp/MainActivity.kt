package com.example.myapp

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.a_feature_api.network.A_FEATURE_PATCH_MASK
import com.example.b_feature_api.dto.B_FEATURE_PATCH_MASK
import com.example.common.ARGS_NAME
import com.example.common.compose.ComposablePatchData
import com.example.common.compose.Router
import com.example.common.compose.registerInNavHost

import com.example.common.config.APP_CHANNEL_NOTIFY
import com.example.myapp.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : ComponentActivity(), Router {
	@Inject
	lateinit var routerMap: Map<String, ComposablePatchData>

	private lateinit var navController: NavHostController

	@OptIn(ExperimentalAnimationApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MainActivityComponent.getInstance().inject(this)
		setContent {
			MaterialTheme {
				Column(modifier = Modifier.fillMaxSize()) {
					navController = rememberNavController()
					NavHost(
						navController = navController,
						startDestination = B_FEATURE_PATCH_MASK,
						modifier = Modifier.weight(1f)
					) {
						// инжект модулей с помошью Dagger @InToMap из Feature модуля
						routerMap.forEach {
							registerInNavHost(it.value, ::routeTo)
						}
					}
					intent.data?.encodedQuery?.let { routeTo(it) }//перехват pendingIntent
					BottomMenu(::routeTo)
				}
			}
		}
	}

	override fun onResume() {
		super.onResume()

		val intent = Intent(
			Intent.ACTION_VIEW,
			Uri.parse("https://vvx.com?$A_FEATURE_PATCH_MASK"),
			this,
			MainActivity::class.java
		)

//		Create an explicit intent for an Activity in your app
//		val intent = Intent(this, MainActivity::class.java).apply {
//			flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//		}
		val pendingIntent: PendingIntent =
			PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

		val builder = NotificationCompat.Builder(this, APP_CHANNEL_NOTIFY)
			.setSmallIcon(R.drawable.ic_launcher_foreground)
			.setContentTitle("Диплинк Тестер")
			.setContentText("Тыкни чтобы открыть Compose экран и передать туда слово - параметр")
			//.setPriority(NotificationCompat.PRIORITY_DEFAULT)
			// Set the intent that will fire when the user taps the notification
			.setContentIntent(pendingIntent)
			.setAutoCancel(true)

		with(NotificationManagerCompat.from(this)) {
			// notificationId is a unique int for each notification that you must define
			if (ActivityCompat.checkSelfPermission(
					this@MainActivity,
					Manifest.permission.POST_NOTIFICATIONS
				) != PackageManager.PERMISSION_GRANTED
			) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
					requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
				}

				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return
			}
			notify(1313, builder.build())
		}
	}

	override fun routeTo(screen: String) {
		navController.navigate(screen) {
			//builder()
			popUpTo("${screen.substringBefore("/")}/{$ARGS_NAME}") {
				inclusive = true
			}
		}
	}
}
