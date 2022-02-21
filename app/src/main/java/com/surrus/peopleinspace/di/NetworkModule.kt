package com.surrus.peopleinspace.di

import com.surrus.common.network.DogService
import com.surrus.common.network.DogServiceImpl
import com.surrus.common.network.models.DogDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeService(): DogService {
        return DogServiceImpl()
    }

    @Singleton
    @Provides
    fun provideRecipeMapper(): DogDtoMapper {
        return DogDtoMapper()
    }


}
