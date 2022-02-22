package com.surrus.peopleinspace.di

import com.surrus.common.interactors.GetDogs
import com.surrus.common.network.DogService
import com.surrus.common.network.models.DogDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideGetDogs(dogService: DogService, dtoMapper: DogDtoMapper, ) =
        GetDogs(dogService = dogService, dtoMapper)
}











