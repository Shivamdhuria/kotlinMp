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
    fun provideGetDogs(recipeService: DogService, dtoMapper: DogDtoMapper, ) =
        GetDogs(recipeService = recipeService, dtoMapper)
//
//  @ViewModelScoped
//  @Provides
//  fun provideRestoreRecipes(
//    recipeDao: RecipeDao,
//    recipeEntityMapper: RecipeEntityMapper,
//  ): RestoreRecipes {
//    return RestoreRecipes(
//      recipeDao = recipeDao,
//      entityMapper = recipeEntityMapper,
//    )
//  }
//
//  @ViewModelScoped
//  @Provides
//  fun provideGetRecipe(
//    recipeDao: RecipeDao,
//    recipeEntityMapper: RecipeEntityMapper,
//    recipeService: RecipeService,
//    recipeDtoMapper: RecipeDtoMapper,
//  ): GetRecipe {
//    return GetRecipe(
//      recipeDao = recipeDao,
//      entityMapper = recipeEntityMapper,
//      recipeService = recipeService,
//      recipeDtoMapper = recipeDtoMapper,
//    )
//  }

}











