package com.example.a_feature_impl.displays.a_feature_main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.c_feature_api.dto.C_FEATURE_PATCH

@Composable
fun AFeatureMainComposeScreen(
	arguments: String?,
	routeHandler: (String) -> Unit,
	viewModel: AFeatureViewModel
) {

	Log.d("assadsa", "Recomposition from .setContent()")
	val state by viewModel.state.collectAsState()
	//Render screen content
	when {
		state.isLoading -> ContentWithProgress()
		state.data.users.isNotEmpty() -> MainScreenContent(
			routeHandler,
			state.menuText,
			state.data,
			state.dialogMsg,
			state.isShowAddDialog,
			onShowDialogClick = { viewModel.onShowDialogClicked(true) },
			onCloseDialogClick = { viewModel.onShowDialogClicked(false) },
			onAcceptDialogClick = { text -> viewModel.onAcceptDialogClicked(text) },
			onUserCheckedChanged = { index, isChecked ->
				viewModel.onItemCheckedChanged(
					index,
					isChecked
				)
			}
		)
	}
}

@Composable
private fun ContentWithProgress() {
	Surface(color = Color.LightGray) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
	}
}

@Composable
private fun MainScreenContent(
	routeHandler: (String) -> Unit,
	manuText: String,
	data: UsersOnWorkDto,
	msgDialog: String,
	isShowAddDialog: Boolean,
	onUserCheckedChanged: (Int, String) -> Unit,
	onShowDialogClick: () -> Unit,
	onCloseDialogClick: () -> Unit,
	onAcceptDialogClick: (String) -> Unit
) {
	Log.d("assadsa", "Recomposition from MainScreenContent()")

	if (isShowAddDialog) {
		MenuTextDialog(
			msgDialog,
			{ onCloseDialogClick.invoke() },
			{ onAcceptDialogClick.invoke(it) })
	}
	ConstraintLayout(modifier = Modifier.fillMaxSize()) {
		val (MenuTextConst, UserListConst, ButtonConst) = createRefs()

		Text(
			text = manuText,
			modifier = Modifier
				.constrainAs(MenuTextConst) {
					top.linkTo(parent.top)
				}
				.clickable {
					onShowDialogClick.invoke()
				})
		LazyColumn(
			modifier = Modifier
				.constrainAs(UserListConst) {
					top.linkTo(MenuTextConst.bottom)
					bottom.linkTo(ButtonConst.top)
				}
				.padding(vertical = 40.dp)
		) {
			itemsIndexed(data.users) { index, item ->
				UserItemCard(item, onUserCheckedChanged, index)
			}
		}

		Button(
			modifier = Modifier
				.constrainAs(ButtonConst) {
					bottom.linkTo(parent.bottom)
				},
			onClick = { routeHandler("$C_FEATURE_PATCH/assd") },
			colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
		) {
			Text(
				text = "go to some display",
				style = TextStyle(color = Color.White, fontSize = 12.sp)
			)
		}
	}
}

@Composable
private fun UserItemCard(
	item: UsersOnWorkDto.User,
	onItemCheckedChanged: (Int, String) -> Unit,
	index: Int,
) {
	Log.d("assadsa", "Recomposition from UserItemCard ${index}")

	Column(
		modifier = Modifier
			.padding(16.dp)
			.clickable {
				onItemCheckedChanged(index, "ты тыкнул на чела с индексом ${item.id} ")
			}
	) {
		Text(
			text = item.id,
			modifier = Modifier.padding(start = 16.dp),
			style = TextStyle(
				color = Color.Black,
				fontSize = 14.sp
			)
		)
		Text(
			text = item.currentJob.title,
			modifier = Modifier.padding(start = 16.dp),
			style = TextStyle(
				color = Color.Black,
				fontSize = 14.sp
			)
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MenuTextDialog(
	msgDialog: String,
	onDialogDismissClick: () -> Unit,
	onDialogOkClick: (String) -> Unit,
) {
	Log.d("assadsa", "Recomposition from MenuTextDialog")

	var text by remember { mutableStateOf("") }
	AlertDialog(onDismissRequest = { },
		text = {
			if (msgDialog.isEmpty()) {
				TextField(
					value = text,
					onValueChange = { newText ->
						text = newText
					},
					colors = TextFieldDefaults.textFieldColors(
						focusedIndicatorColor = Color.Blue,
						disabledIndicatorColor = Color.Blue,
						unfocusedIndicatorColor = Color.Blue,
						//backgroundColor = Color.LightGray,
					)
				)
			} else {
				Text(
					text = msgDialog
				)
			}
		},
		confirmButton = {
			if (msgDialog.isEmpty()) {
				Button(
					onClick = { onDialogOkClick(text) },
					colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
				) {
					Text(text = "Ok", style = TextStyle(color = Color.White, fontSize = 12.sp))
				}
			}
		}, dismissButton = {
			Button(
				onClick = { onDialogDismissClick.invoke() },
				colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
			) {
				Text(text = "Cancel", style = TextStyle(color = Color.White, fontSize = 12.sp))
			}
		}
	)
}
