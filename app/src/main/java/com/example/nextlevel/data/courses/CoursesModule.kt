package com.example.nextlevel.data.courses

import com.example.nextlevel.data.courses.repository.CoursesRepository
import com.example.nextlevel.data.courses.repository.CoursesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoursesModule {

	@Binds
	fun bindRepository(impl: CoursesRepositoryImpl): CoursesRepository
}