package com.example.nextlevel.network

import com.example.nextlevel.domain.auth.LogoutHandler
import com.example.nextlevel.domain.auth.LogoutHandlerImpl
import com.example.nextlevel.domain.auth.TokenRefresher
import com.example.nextlevel.domain.auth.TokenRefresherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlingModule {

	@Binds
	@Singleton
	abstract fun bindTokenRefresher(impl: TokenRefresherImpl): TokenRefresher

	@Binds
	@Singleton
	abstract fun bindLogoutHandler(impl: LogoutHandlerImpl): LogoutHandler
}
