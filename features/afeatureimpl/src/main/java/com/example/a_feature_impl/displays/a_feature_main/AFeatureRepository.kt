package com.example.a_feature_impl.displays.a_feature_main

import com.example.a_feature_impl.network.AFeatureInteractor
import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.common.lce.Lce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AFeatureRepository {
	fun getUsers(): Flow<Lce<UsersOnWorkDto>>
}

class AFeatureRepositoryImpl @Inject constructor(private val interactor: AFeatureInteractor) :
	AFeatureRepository {
	override fun getUsers() = interactor.getUsers()

}