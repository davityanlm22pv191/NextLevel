package com.example.nextlevel.data.auth

import com.example.nextlevel.data.auth.repository.AuthRepository
import com.example.nextlevel.data.auth.repository.AuthRepositoryImpl
import com.example.nextlevel.data.auth.service.AuthService
import com.example.nextlevel.data.auth.service.AuthServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

	@Binds
	@Singleton
	fun bindService(impl: AuthServiceImpl): AuthService

	@Binds
	fun bindRepository(impl: AuthRepositoryImpl): AuthRepository
}