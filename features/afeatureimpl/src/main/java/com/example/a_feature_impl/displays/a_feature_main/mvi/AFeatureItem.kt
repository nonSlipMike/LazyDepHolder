package com.example.a_feature_impl.displays.a_feature_main.mvi

sealed class AFeatureItem {
	object AFeatureAddButtonItem : AFeatureItem()
	data class AFeatureTodoItem(
		val isChecked: Boolean,
		val text: String,
	) : AFeatureItem()
}