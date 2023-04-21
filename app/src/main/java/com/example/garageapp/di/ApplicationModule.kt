package com.example.garageapp.di

import com.example.garageapp.Repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesFirebaseRepository() = FirebaseRepository()
}