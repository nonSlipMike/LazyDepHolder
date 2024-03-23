package com.example.composeroot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import com.example.c_feature_api.dto.C_FEATURE_PATCH_NAME

@Composable
fun BottomMenu(
	routeHandler: (String, NavOptionsBuilder.() -> Unit) -> Unit,
) {

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		Text(
			text = "Главная",
			modifier = Modifier.clickable {
				routeHandler("homescreen") {}
			})
		Text(
			text = "C_Feature ",
			modifier = Modifier.clickable {
				routeHandler(C_FEATURE_PATCH_NAME) {}
			})
	}
}