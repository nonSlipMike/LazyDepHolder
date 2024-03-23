package com.example.common.compose

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

fun Fragment.setContent(
	compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
	context: Context? = getContext(),
	content: @Composable () -> Unit
): ComposeView? {
	context ?: return null
	val view = ComposeView(context)
	view.setViewCompositionStrategy(compositionStrategy)
	view.setContent { MaterialTheme { content.invoke() } }
	return view
}


