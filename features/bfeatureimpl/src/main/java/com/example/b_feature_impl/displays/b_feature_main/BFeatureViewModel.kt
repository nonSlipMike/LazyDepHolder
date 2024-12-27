package com.example.b_feature_impl.displays.b_feature_main

import androidx.lifecycle.ViewModel
import com.example.database_api.dao.MainListDao
import com.example.database_api.entity.MainListItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BFeatureViewModel @Inject constructor(dao: MainListDao) : ViewModel() {

	private val _uiState = MutableStateFlow(ItemListState())
	val uiState: StateFlow<ItemListState> = _uiState

	// Инициализация списка элементов
	init {
		_uiState.value = ItemListState(items = generateDummyItems())
	}

	// Метод для добавления нового элемента
	fun addNewItem() {
		val newItem = MainListItemEntity(
			id = _uiState.value.items.size + 1,
			name = "Новый элемент",
			value = (0..100).random().toDouble()
		)
		_uiState.value = _uiState.value.copy(items = _uiState.value.items + newItem)
	}

	// Генерация некоторых тестовых данных
	private fun generateDummyItems(): List<MainListItemEntity> {
		return List(5) {
			MainListItemEntity(
				id = it,
				name = "Элемент #${it + 1}",
				value = (0..100).random().toDouble()
			)
		}
	}
}

// Стейт для UI
data class ItemListState(
	val items: List<MainListItemEntity> = emptyList()
)

// Модель элемента
data class Item(
	val id: Int,
	val name: String,
	val value: Double
)
