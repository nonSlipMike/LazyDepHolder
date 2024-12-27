package com.example.b_feature_impl.displays.b_feature_main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.b_feature_impl.R
import com.example.c_feature_api.dto.C_FEATURE_PATCH
import com.example.common.compose.pakObjectToGson
import com.example.database_api.entity.MainListItemEntity


@Composable
fun BFeatureMainComposeScreen(
	arguments: String?,
	routeHandler: (String) -> Unit,
	viewModel: BFeatureViewModel
) {
	Log.d("assadsa", "Recomposition from BFeatureMainComposeScreen ")

	val state = viewModel.uiState.collectAsState()
	ItemListScreen(state.value, viewModel::addNewItem, routeHandler)
}

@Composable
fun ItemListScreen(state: ItemListState, addNewItem: () -> Unit, routeHandler: (String) -> Unit) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.background(Color(0xFFFFE0B2))
	) {
		itemsIndexed(state.items) { index, item ->
			ItemTile(item) {
				routeHandler("$C_FEATURE_PATCH/${item}")
			}
		}

		item {
			Divider(modifier = Modifier.padding(vertical = 8.dp))
			AddItemButton {
				addNewItem()
			}
		}
	}
}

@Composable
fun AddItemButton(onClick: () -> Unit) {
	Button(
		onClick = { onClick() },
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		shape = RoundedCornerShape(12.dp),
		contentPadding = PaddingValues(vertical = 16.dp)
	) {
		Text(text = "Добавить элемент для отслеживания", style = MaterialTheme.typography.bodyLarge)
	}
}

@Composable
fun ItemTile(item: MainListItemEntity, onClick: () -> Unit) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(80.dp)
			.padding(16.dp)
			.clickable {
				onClick()
					   },
		shape = RoundedCornerShape(12.dp),
		elevation = CardDefaults.cardElevation(5.dp),
	) {
		Row(
			modifier = Modifier
				.weight(1F)
				.fillMaxSize()
				.padding(8.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			item.name?.let {
				Text(
					text = it,
					modifier = Modifier.weight(1f),
					style = MaterialTheme.typography.bodyMedium,
					textAlign = TextAlign.Center
				)
			}
			Row(
				modifier = Modifier.weight(1F),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center
			) {
				Image(
					painter = painterResource(id = R.drawable.ic_vector_red_arrow),
					contentDescription = "Arrow Down",
					modifier = Modifier
						.size(50.dp)
						.offset(y = (-4).dp)
				)

				Text(
					text = "%.1f".format(item.value),
					style = MaterialTheme.typography.bodyMedium,
					textAlign = TextAlign.Center
				)

				Text(
					text = "%",
					style = MaterialTheme.typography.bodyMedium,
				)

				Image(
					painter = painterResource(id = R.drawable.ic_vector_green_arrow),
					contentDescription = "Arrow Up",
					modifier = Modifier.size(50.dp)
				)
			}
		}
	}
}

