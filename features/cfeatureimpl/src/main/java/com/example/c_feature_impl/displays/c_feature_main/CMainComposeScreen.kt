package com.example.c_feature_impl.displays.c_feature_main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptionsBuilder
import okhttp3.OkHttp
import okhttp3.OkHttpClient

@Composable
fun CFeatureMainComposeScreen(
	routeHandler: (String, NavOptionsBuilder.() -> Unit) -> Unit,
	viewModel: CFeatureViewModel
) {
	OkHttpClient
	Log.d("assadsa", "Recomposition from CFeatureMainComposeScreen ")

	val state = viewModel.uiState.collectAsState()
	val addedDigit = remember { mutableStateOf("0") }

	Column {
		Text(text = state.value.sum.toString())
		Text(text = "last digit is: ${state.value.lastIncrease}")
		TextField(
			value = addedDigit.value, onValueChange = { addedDigit.value = it },
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
		)
		Button(
			onClick = { viewModel.storeItemToList(addedDigit.value) },
			colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
		) {
			Text(
				text = "Add Digit to Sum",
				style = TextStyle(color = Color.White, fontSize = 12.sp)
			)
		}
		Text(
			text = "go to orders screen",
			modifier = Modifier.clickable { routeHandler("orders") {} })

		ListOfDigits(state.value.items)


	}

}



@Composable//объекты коллекций передавать только в Immutable обертках!!!!!
fun ListOfDigits(listWithDigits: ItemsCollection) {
	Log.d("assadsa", "Recomposition from ListOfDigits ")

	LazyColumn {
		itemsIndexed(listWithDigits.collection) { index, item ->
			ItemCard(index, item)
		}
	}
}

@Composable
private fun ItemCard(
	index: Int,
	item: String
) {
	Log.d("assadsa", "Recomposition from ItemCard ${index}")

	Text(
		text = item,
		modifier = Modifier.padding(start = 16.dp),
		style = TextStyle(
			color = Color.Black,
			fontSize = 14.sp
		)
	)
}

