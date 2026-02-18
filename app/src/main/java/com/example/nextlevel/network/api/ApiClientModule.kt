package com.example.nextlevel.network.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiClientModule {
	@Binds
	@Singleton
	abstract fun bind(impl: ApiClientImpl): ApiClient
}