package com.example.nextlevel.data.fortunewheel

import com.example.nextlevel.data.fortunewheel.repository.FortuneWheelRepository
import com.example.nextlevel.data.fortunewheel.repository.FortuneWheelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface FortuneWheelModule {

	@Singleton
	@Binds
	fun bindRepository(impl: FortuneWheelRepositoryImpl): FortuneWheelRepository
}