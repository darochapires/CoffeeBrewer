package com.rochapires.coffeebrewer.di

import android.content.Context
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.feature_recipe.data.remote.CoffeeApi
import com.rochapires.coffeebrewer.features.feature_recipe.data.repository.RecipeRepositoryImpl
import com.rochapires.coffeebrewer.features.feature_recipe.data.repository.UserPreferencesRepositoryImpl
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.*
import com.rochapires.coffeebrewer.features.feature_onboarding.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoffeeApi(): CoffeeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: CoffeeApi): RecipeRepository {
        return RecipeRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(@ApplicationContext app: Context): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(app)
    }

    @Provides
    @Singleton
    fun provideRecipeUseCase(recipeRepository: RecipeRepository, userPreferencesRepository: UserPreferencesRepository): RecipeUseCases {
        return RecipeUseCases(
            getMethodsUseCase = GetMethodsUseCase(recipeRepository),
            getMethodUseCase = GetMethodUseCase(recipeRepository),
            getRecipesByMethodIdUseCase = GetRecipesByMethodIdUseCase(recipeRepository),
            getRecipeUseCase = GetRecipeUseCase(recipeRepository),
            addRecipeUseCase = AddRecipeUseCase(recipeRepository),
            deleteRecipeUseCase = DeleteRecipeUseCase(recipeRepository),
            saveDefaultMethodUseCase = SaveDefaultMethodUseCase(userPreferencesRepository),
            getDefaultMethodUseCase = GetDefaultMethodUseCase(userPreferencesRepository),
            saveDefaultRecipeUseCase = SaveDefaultRecipeUseCase(userPreferencesRepository),
            getDefaultRecipeUseCase = GetDefaultRecipeUseCase(userPreferencesRepository),
            saveDefaultCoffeeQuantityUseCase = SaveDefaultCoffeeQuantityUseCase(userPreferencesRepository),
            getDefaultCoffeeQuantityUseCase = GetDefaultCoffeeQuantityUseCase(userPreferencesRepository)
        )
    }

    @Provides
    @Singleton
    fun provideOnboardingUseCase(userPreferencesRepository: UserPreferencesRepository): OnboardingUseCases {
        return OnboardingUseCases(
            saveOnboardingDoneUseCase = SaveOnboardingDoneUseCase(userPreferencesRepository),
            getOnboardingDoneUseCase = GetOnboardingDoneUseCase(userPreferencesRepository)
        )
    }
}