package com.example.nextlevel.domain.auth

/**
 * Attempts to refresh the authentication token.
 * Returns `true` if the new token was obtained and saved, `false` otherwise.
 */
interface TokenRefresher {
	suspend fun refresh(): Boolean
}
