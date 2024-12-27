package com.example.common.compose

import com.google.gson.Gson

inline fun <reified T> String.getObjectFromGson(): T {
	return Gson().fromJson(this, T::class.java)
}


fun Any.pakObjectToGson(): String =
	Gson().toJson(this )