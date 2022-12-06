package com.rochapires.coffeebrewer.features.feature_method.data.data_source
/*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.relations.MethodWithRecipes
import kotlinx.coroutines.flow.Flow

@Dao
interface MethodDao {

    @Query("SELECT * FROM method")
    fun getMethods(): Flow<List<Method>>

    @Query("SELECT * FROM method WHERE id = :id")
    suspend fun getMethodById(id: Int): Method?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Transaction
    @Query("SELECT * FROM recipe WHERE methodId = :id")
    suspend fun getRecipesByMethodId(id: Int): List<MethodWithRecipes>
}*/