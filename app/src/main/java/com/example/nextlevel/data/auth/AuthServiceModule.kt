package com.example.nextlevel.data.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthServiceModule {

	@Binds
	@Singleton
	fun bind(impl: AuthServiceImpl): AuthService
}