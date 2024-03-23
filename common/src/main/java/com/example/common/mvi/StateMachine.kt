package com.example.common.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateMachine<S : UiState, E : UiEvent>(initialVal: S) {

	private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
	val state: StateFlow<S>
		get() = _state

	fun sendEvent(event: E) {
		handle(_state.value, event)
	}

	fun setState(newState: S) {
		_state.tryEmit(newState)
	}

	abstract fun handle(oldState: S, event: E)
}

interface UiState

interface UiEvent // this is intent(or intention) in "MVI"