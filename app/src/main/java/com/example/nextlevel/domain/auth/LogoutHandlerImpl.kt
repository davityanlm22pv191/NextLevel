package com.example.nextlevel.domain.auth

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutHandlerImpl @Inject constructor() : LogoutHandler {

	override fun onLogoutRequested() {
		// TODO: реализовать полноценный логаут:
		//  1. Очистить токены из CredentialsStorage
		//  2. Очистить кеши (ProfileStorage, MailStorage и т.д.)
		//  3. Перенаправить пользователя на экран авторизации
		Log.w("LogoutHandler", "Logout requested — not yet implemented")
	}
}
