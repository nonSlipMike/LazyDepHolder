package com.example.a_feature_impl.displays.a_feature_main.mvi

import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.common.mvi.UiEvent

sealed class AFeatureEvent : UiEvent {
	data class ShowData(val items: UsersOnWorkDto) : AFeatureEvent()
	data class OnChangeDialogVisibleState(val show: Boolean) : AFeatureEvent()
	data class UpdateMainMenuText(val text: String) : AFeatureEvent()
	data class OnItemClicked(val index: Int, val msg: String) : AFeatureEvent()
	object DismissDialog : AFeatureEvent()
}
