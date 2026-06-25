package com.snapwork.gcashweatherapp.di

import android.content.Context
import com.snapwork.gcashweatherapp.presentation.weather.location.LocationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun provideLocationHelper(
        @ApplicationContext context: Context
    ) = LocationHelper(context)
}