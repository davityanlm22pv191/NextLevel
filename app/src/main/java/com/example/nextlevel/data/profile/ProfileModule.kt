package com.example.nextlevel.data.profile

import com.example.nextlevel.data.profile.storage.ProfileStorage
import com.example.nextlevel.data.profile.storage.ProfileStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ProfileModule {
	@Binds
	@Singleton
	abstract fun bind(impl: ProfileStorageImpl): ProfileStorage
}