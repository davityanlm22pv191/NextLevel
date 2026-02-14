package com.example.nextlevel.data.credentials

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CredentialsStorageModule {

	@Binds
	@Singleton
	abstract fun bind(impl: CredentialsStorageImpl): CredentialsStorage
}