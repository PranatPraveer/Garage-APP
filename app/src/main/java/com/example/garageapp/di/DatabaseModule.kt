package com.example.garageapp.di

import android.content.Context
import androidx.room.Room
import com.example.garageapp.db.carDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCarDB(@ApplicationContext context: Context):carDB{
        return Room.databaseBuilder(context, carDB::class.java,"carDB").build()
    }
}