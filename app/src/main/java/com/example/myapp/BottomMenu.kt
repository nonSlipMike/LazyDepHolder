package com.example.myapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a_feature_api.network.A_FEATURE_PATCH
import com.example.b_feature_api.dto.B_FEATURE_PATCH
import com.example.c_feature_api.dto.C_FEATURE_PATCH

@Composable
fun BottomMenu(
	routeHandler: (String) -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		Text(
			text = "A_Feature",
			modifier = Modifier.clickable {
				routeHandler("$A_FEATURE_PATCH/someMassage1")
			})
		Text(
			text = "B_Feature ",
			modifier = Modifier.clickable {
				routeHandler("$B_FEATURE_PATCH/some")
			})
		Text(
			text = "C_Feature ",
			modifier = Modifier.clickable {
				routeHandler("$C_FEATURE_PATCH/someMassage3")
			})
	}
}