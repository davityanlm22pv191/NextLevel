package com.example.nextlevel.domain.auth

/**
 * Handles logout when token refresh fails or repeated 401.
 * Implement actual logout logic (clear credentials, navigate to login, etc.).
 */
interface LogoutHandler {
	fun onLogoutRequested()
}
