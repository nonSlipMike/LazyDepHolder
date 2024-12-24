package com.example.database_impl.di

import androidx.room.TypeConverter
import com.example.database_api.entity.MainListItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainListItemConverters {

	private val gson = Gson()

	@TypeConverter
	fun fromMainListItem(item: MainListItemEntity): String {
		return gson.toJson(item) // Преобразуем объект MainListItem в строку JSON
	}

	@TypeConverter
	fun toMainListItem(data: String): MainListItemEntity {
		return gson.fromJson(
			data,
			MainListItemEntity::class.java
		) // Преобразуем JSON-строку обратно в объект
	}

	@TypeConverter
	fun fromMainListItemList(list: List<MainListItemEntity>): String {
		return gson.toJson(list) // Преобразуем список объектов в строку JSON
	}

	@TypeConverter
	fun toMainListItemList(data: String): List<MainListItemEntity> {
		val listType = object : TypeToken<List<MainListItemEntity>>() {}.type
		return gson.fromJson(data, listType) // Преобразуем JSON-строку обратно в список объектов
	}
}