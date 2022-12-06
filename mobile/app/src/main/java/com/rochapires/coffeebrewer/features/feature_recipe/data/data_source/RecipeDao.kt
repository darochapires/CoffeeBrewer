package com.rochapires.coffeebrewer.features.feature_recipe.data.data_source
/*
import androidx.room.*
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getRecipeById(id: Int): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

}*/