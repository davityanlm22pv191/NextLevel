package com.example.tutorplace.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tutorplace.BuildConfig
import com.example.tutorplace.data.mail.model.MailType
import com.example.tutorplace.network.deserializers.LocalDateTimeDeserializer
import com.example.tutorplace.network.deserializers.MailTypeDeserializer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
		return ChuckerInterceptor.Builder(context)
			.collector(ChuckerCollector(context, showNotification = true))
			.maxContentLength(250_000L)
			.build()
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(chucker: ChuckerInterceptor): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(chucker)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(client: OkHttpClient): Retrofit {
		val gson = GsonBuilder()
			.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
			.registerTypeAdapter(MailType::class.java, MailTypeDeserializer())
			.create()
		return Retrofit.Builder()
			.baseUrl(BuildConfig.SERVER_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(client)
			.build()
	}
}