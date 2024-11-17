package com.example.a_feature_impl.network

import com.example.a_feature_impl.reststubs.toDto
import com.example.common.lce.lce
import javax.inject.Inject

class AFeatureInteractorImpl @Inject constructor(private val AFeatureRest: AFeatureRest) :
	AFeatureInteractor {

	override fun getUsers() = lce {
		AFeatureRest.getMyData().toDto()
	}
}