package com.example.common.di

fun <K, V> ArrayList<Map<K, V>>.getOneMap(): Map<K, V> {
	val resultMap = mutableMapOf<K, V>()
	forEach { resultMap.putAll(it) }
	return resultMap
}