package com.rochapires.coffeebrewer.features.feature_recipe.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_PREFERENCES_NAME)

class UserPreferencesRepositoryImpl @Inject constructor(
    private val context: Context,
    ) : UserPreferencesRepository {


    companion object {
        val defaultMethodKey = intPreferencesKey(Constants.DEFAULT_METHOD_KEY)
        //val defaultRecipeKey = intPreferencesKey(Constants.DEFAULT_RECIPE_KEY)
        val defaultCoffeeQuantityKey = doublePreferencesKey(Constants.DEFAULT_COFFEE_QUANTITY_KEY)
    }

    override suspend fun getDefaultMethod(): Int? {
        return read(defaultMethodKey)
    }

    override suspend fun setDefaultMethod(methodId: Int) {
        save(defaultMethodKey, methodId)
    }

    /*override suspend fun getDefaultRecipe(): Int? {
        return read(defaultRecipeKey)
    }

    override suspend fun setDefaultRecipe(recipeId: Int) {
        save(defaultRecipeKey, recipeId)
    }*/

    override suspend fun getDefaultCoffeeQuantity(): Double? {
        return read(defaultCoffeeQuantityKey)
    }

    override suspend fun setDefaultCoffeeQuantity(coffeeQuantity: Double) {
        save(defaultCoffeeQuantityKey, coffeeQuantity)
    }

    private suspend fun <T> read(key: Preferences.Key<T>): T? {
        val preferences = context.dataStore.data.first()
        return preferences[key]
    }

    private suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}