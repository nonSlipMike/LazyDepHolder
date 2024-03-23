package com.example.a_feature_impl.network

import com.example.a_feature_impl.reststubs.UsersOnWorkDto
import com.example.a_feature_impl.reststubs.UsersOnWorkResponse
import com.example.common.lce.Lce
import kotlinx.coroutines.flow.Flow

interface AFeatureInteractor {
	fun getUsers(): Flow<Lce<UsersOnWorkDto>>
}