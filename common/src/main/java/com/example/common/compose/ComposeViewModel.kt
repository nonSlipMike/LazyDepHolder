package com.example.common.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//->Фабрика вьюмоделей по примеру хилт
//  выполняет роль отложенной инициализации и цепляет вью модель за Ж/Ц записи в бекстеке
//->При использовании следить за @Scope и за опциями метода NavHostController.navigate -
//  они помогут контролировать содержимое бекстека и поведение Ж/Ц вью модели
//->Из за использования анимации до бекстека compose не добраться на прямую, актуально для
//  com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha
@Composable
@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> provideViewModelWithDependency(
	key: String? = null,
	crossinline viewModelInstanceCreator: () -> T
): T =
	androidx.lifecycle.viewmodel.compose.viewModel(
		modelClass = T::class.java,
		key = key,
		factory = object : ViewModelProvider.Factory {
			override fun <T : ViewModel> create(modelClass: Class<T>): T {
				return viewModelInstanceCreator() as T
			}
		}
	)
