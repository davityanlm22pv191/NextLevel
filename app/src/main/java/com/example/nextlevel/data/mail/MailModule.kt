package com.example.nextlevel.data.mail

import com.example.nextlevel.data.mail.storage.MailStorage
import com.example.nextlevel.data.mail.storage.MailStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MailModule {

	@Binds
	@Singleton
	abstract fun bind(impl: MailStorageImpl): MailStorage
}