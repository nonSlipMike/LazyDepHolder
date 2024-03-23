package com.example.a_feature_impl.network

import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.a_feature_impl.reststubs.toDto
import com.example.common.lce.Lce
import com.example.common.lce.lce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class AFeatureInteractorImpl @Inject constructor(private val AFeatureRest: AFeatureRest) :
	AFeatureInteractor {

	override fun getUsers() = lce {
		AFeatureRest.getMyData().toDto()
	}
}