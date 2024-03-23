package com.example.a_feature_impl.displays.a_feature_main.mvi

import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.a_feature_impl.reststubs.UsersOnWorkResponse
import com.example.a_feature_impl.reststubs.toDto
import com.example.common.mvi.UiState

//@Immutable
data class AFeatureState(
	val menuText: String,
	val isLoading: Boolean,
	val data: UsersOnWorkDto,
	val isShowAddDialog: Boolean,
	val dialogMsg: String
) : UiState {
	companion object {
		fun initial() = AFeatureState(
			menuText = "Если тыкнуть сюда то можно че нить написать",
			isLoading = true,
			data = UsersOnWorkResponse().toDto(),
			isShowAddDialog = false,
			dialogMsg = ""
		)
	}
}