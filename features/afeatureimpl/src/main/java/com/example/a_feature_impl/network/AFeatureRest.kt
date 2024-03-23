package com.example.a_feature_impl.network

import com.example.a_feature_impl.reststubs.UsersOnWorkResponse
import retrofit2.http.GET

interface AFeatureRest {
	@GET("myData")
	suspend fun getMyData(
	): UsersOnWorkResponse
}