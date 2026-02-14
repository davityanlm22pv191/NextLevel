package com.example.nextlevel.ui.base

interface BaseReducer<State: BaseState, Event: BaseEvent> {
	fun reduce(oldState: State, event: Event): State
}