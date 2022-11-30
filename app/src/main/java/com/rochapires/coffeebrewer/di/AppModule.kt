package com.rochapires.coffeebrewer.di

import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.*
import com.rochapires.coffeebrewer.features.feature_recipe.data.remote.CoffeeApi
import com.rochapires.coffeebrewer.features.feature_recipe.data.repository.RecipeRepositoryImpl
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideRecipeDatabase(app: Application): CoffeeDatabase {
//        return Room.databaseBuilder(
//            app,
//            CoffeeDatabase::class.java,
//            CoffeeDatabase.DATABASE_NAME
//        ).build()
//    }

//    @Provides
//    @Singleton
//    fun provideMethodRepository(db: CoffeeDatabase): MethodRepository {
//        return MethodRepositoryImpl(db.methodDao)
//    }

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

//    @Provides
//    @Singleton
//    fun provideMethodUseCase(repository: MethodRepository): MethodUseCases {
//        return MethodUseCases(
//            getMethodsUseCase = GetMethodsUseCase(repository),
//            getMethodUseCase = GetMethodUseCase(repository)
//        )
//    }

    @Provides
    @Singleton
    fun provideRecipeUseCase(repository: RecipeRepository): RecipeUseCases {
        return RecipeUseCases(
            getMethodsUseCase = GetMethodsUseCase(repository),
            getMethodUseCase = GetMethodUseCase(repository),
            getRecipesByMethodIdUseCase = GetRecipesByMethodIdUseCase(repository),
            getRecipeUseCase = GetRecipeUseCase(repository),
            addRecipeUseCase = AddRecipeUseCase(repository),
            deleteRecipeUseCase = DeleteRecipeUseCase(repository)
        )
    }
}