package com.example.nextlevel.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<Event : BaseEvent, State : BaseState, Effect : BaseEffect> :
	ViewModel() {

	private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
	val effect = _effects.receiveAsFlow()

	protected fun sendEffect(effect: Effect) {
		_effects.trySend(effect)
	}

	private val _state = MutableStateFlow(initialState())
	val state: StateFlow<State> = _state.asStateFlow()

	abstract fun initialState(): State

	abstract fun onEvent(event: Event)

	protected fun setState(newState: State) {
		if (newState != _state.value) {
			_state.tryEmit(newState)
		}
	}

	override fun onCleared() {
		super.onCleared()
	}
}