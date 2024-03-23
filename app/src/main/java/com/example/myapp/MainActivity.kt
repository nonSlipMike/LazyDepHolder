package com.example.myapp

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.example.common.compose.Router

import com.example.common.config.APP_CHANNEL_NOTIFY
import com.example.myapp.di.MainActivityComponent
import javax.inject.Inject
import kotlin.math.log

class MainActivity : AppCompatActivity(R.layout.activity_main), Router {

	private lateinit var activityNavController: NavController

	@Inject
	lateinit var routesByFragments: Map<String, ((Bundle?) -> Fragment)?>


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MainActivityComponent.getInstance().inject(this)



	}


	override fun onResume() {
		super.onResume()
		//supportFragmentManager.beginTransaction()

		val intent = Intent(
			Intent.ACTION_VIEW,
			Uri.parse("https://vvx.com?параметр"),
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

	override fun routeToCompose(screen: String) {
		val request = NavDeepLinkRequest.Builder
			.fromUri(screen.toUri())
			.build()

		activityNavController.navigate(request)


	}

	override fun roteToFragment(path: String, arguments: Bundle?) {
		routesByFragments[path]?.invoke(arguments)?.let {
			supportFragmentManager.apply {
				popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
				beginTransaction()
					.replace(R.id.nav_host_fragment, it).addToBackStack(null).commit()
			}
		}
	}
}
