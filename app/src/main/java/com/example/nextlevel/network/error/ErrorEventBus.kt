package com.example.nextlevel.network.error

import com.example.nextlevel.domain.model.NetworkError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Holds the user-visible error message shown in the banner.
 */
data class ErrorEvent(val message: String) {
}

/**
 * Application-wide event bus for error banner display.
 *
 * Any layer (ViewModel, UseCase) can send an [ErrorEvent] via [send],
 * and the UI layer observes [events] to show/hide the banner.
 *
 * If a banner is already being displayed ([isBannerShowing] == true),
 * new events are silently dropped so the user is not flooded with errors.
 *
 * Provided as a Singleton via Hilt so that all consumers share the same channel.
 */
@Singleton
class ErrorEventBus @Inject constructor() {

	private val _events = Channel<ErrorEvent>(capacity = Channel.RENDEZVOUS)
	val events = _events.receiveAsFlow()

	private val _isBannerShowing = AtomicBoolean(false)

	/**
	 * Called by the UI layer (ErrorBanner) when the banner becomes visible.
	 */
	fun onBannerShown() {
		_isBannerShowing.set(true)
	}

	/**
	 * Called by the UI layer (ErrorBanner) when the banner has fully hidden.
	 */
	fun onBannerHidden() {
		_isBannerShowing.set(false)
	}

	/**
	 * Sends an [ErrorEvent] only if the banner is not currently showing.
	 * Returns `true` if the event was accepted, `false` if it was dropped.
	 */
	fun send(event: ErrorEvent): Boolean {
		if (_isBannerShowing.get()) return false
		_events.trySend(event)
		return true
	}

	/**
	 * Convenience: maps a [NetworkError] to a localized message and sends it.
	 * Call [onLogoutRequested] for 401 errors — the banner is not shown for auth errors,
	 * because the user is redirected to the login screen instead.
	 */
	fun sendForError(
		error: NetworkError,
		networkConnectionMessage: String,
		timeoutMessage: String,
		unknownErrorMessage: String,
	) {
		when (error) {
			is NetworkError.AuthError401 -> {
				// Auth errors are handled via logout callback, no banner needed
			}
			is NetworkError.NetworkConnectionError -> {
				send(ErrorEvent(networkConnectionMessage))
			}
			is NetworkError.TimeoutError -> {
				send(ErrorEvent(timeoutMessage))
			}
			is NetworkError.UnknownError -> {
				send(ErrorEvent(unknownErrorMessage))
			}
		}
	}
}
