package com.example.tutorplace.network

import com.example.tutorplace.data.fortunewheel.FortuneWheelService
import com.example.tutorplace.data.courses.CoursesService
import com.example.tutorplace.data.onboarding.OnboardingService
import com.example.tutorplace.data.profile.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ApiDataModule {

	@Provides
	fun provideOnboardingService(retrofit: Retrofit): OnboardingService {
		return retrofit.create(OnboardingService::class.java)
	}

	@Provides
	fun provideProfileService(retrofit: Retrofit): ProfileService {
		return retrofit.create(ProfileService::class.java)
	}

	@Provides
	fun provideFortuneWheelService(retrofit: Retrofit): FortuneWheelService {
		return retrofit.create(FortuneWheelService::class.java)
	}

	@Provides
	fun provideCoursesService(retrofit: Retrofit): CoursesService {
		return retrofit.create(CoursesService::class.java)
	}
}