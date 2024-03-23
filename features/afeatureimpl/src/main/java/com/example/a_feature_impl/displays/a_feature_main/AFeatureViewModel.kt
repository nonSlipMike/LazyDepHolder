package com.example.a_feature_impl.displays.a_feature_main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.a_feature_impl.displays.a_feature_main.mvi.AFeatureEvent
import com.example.a_feature_impl.displays.a_feature_main.mvi.AFeatureState
import com.example.common.mvi.BaseViewModel
import com.example.common.mvi.StateMachine
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Math.sqrt
import kotlin.math.log

class AFeatureViewModel @AssistedInject constructor(
	repository: AFeatureRepository,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<AFeatureState, AFeatureEvent>() {

	private val stateMachine = MainReducer(AFeatureState.initial())

	override val state: StateFlow<AFeatureState>
		get() = stateMachine.state



	sealed class asd


	init {
		solveTask(1.0, 1.0, 1.0)

		strFun("asdasd")
		viewModelScope.launch {
			repository.getUsers().collect {
				it.data?.let { data -> stateMachine.sendEvent(AFeatureEvent.ShowData(data)) }
			}
		}
	}

//	4. Реализовать функцию решения уравнения вида ax^2 + bx + c = 0.
//	Результат работы распечатывается. public void solve (double a, double b, double c).
//
//	Аналитик: Ребята чтобы не гуглить вот формулы
//	d = b*b -4ac; x1,2 = (-b +- sqrt(d))/(2a)


	fun solveTask(a: Double, b: Double, c: Double) {
		val desc = (b * b) - (4 * (a * c))
		if (desc < 0) {
			Log.d("asdasdasd", "корней нету")
			return
		}
//		if (desc)
//
//		х = −b/2a;
		val reshenie1 = ((-b) + (-1 * sqrt(desc))) / (2 * a)
		val reshenie2 = ((-b) + (+1 * sqrt(desc))) / (2 * a)
		Log.d("asdasdasd", "reshenie1 - ${reshenie1}, reshenie2 - ${reshenie2}")
	}


	fun strFun(str: String) {
		val stringsMap = mutableMapOf<String, Int>()
		var currentIndex = 1

		for (i in str.indices) {
			if (i < currentIndex) {
				continue
			} else {
				//currentIndex = addOrUpdateSubStr(str[i].toString(), stringsMap)
				addOrUpdateSubStr(str[i].toString(), stringsMap)
			}


		}
	}

	//орпределяет есть ли последовательность и возвращает был ли элемент в последовательности
	fun addOrUpdateSubStr(subStr: String, map: MutableMap<String, Int>): Boolean {
		if (map.contains(subStr)) {
			val frequency = map[subStr]!!
			map.put(subStr, frequency + 1)
			return true
		} else {
			map.put(subStr, 1)
			return false
		}
	}


	fun onShowDialogClicked(show: Boolean) {
		stateMachine.sendEvent(AFeatureEvent.OnChangeDialogVisibleState(show))
	}

	fun onAcceptDialogClicked(text: String) {
		stateMachine.sendEvent(AFeatureEvent.UpdateMainMenuText(text))
	}

	fun onItemCheckedChanged(index: Int, isChecked: String) {
		stateMachine.sendEvent(AFeatureEvent.OnItemClicked(index, isChecked))
	}

	private class MainReducer(initial: AFeatureState) :
		StateMachine<AFeatureState, AFeatureEvent>(initial) {
		override fun handle(oldState: AFeatureState, event: AFeatureEvent) {
			when (event) {
				is AFeatureEvent.UpdateMainMenuText -> {
					setState(oldState.copy(menuText = event.text, isShowAddDialog = false))
				}
				is AFeatureEvent.OnChangeDialogVisibleState -> {
					setState(oldState.copy(isShowAddDialog = event.show, dialogMsg = ""))
				}
				is AFeatureEvent.DismissDialog -> {
					setState(oldState.copy(isShowAddDialog = false))
				}
				is AFeatureEvent.ShowData -> {
					setState(oldState.copy(isLoading = false, data = event.items))
				}
				is AFeatureEvent.OnItemClicked -> {
					setState(
						oldState.copy(
							isShowAddDialog = true,
							dialogMsg = event.msg
						)
					)
				}
			}
		}
	}

	@AssistedFactory
	interface Factory {
		fun create(savedStateHandle: SavedStateHandle): AFeatureViewModel
	}
}