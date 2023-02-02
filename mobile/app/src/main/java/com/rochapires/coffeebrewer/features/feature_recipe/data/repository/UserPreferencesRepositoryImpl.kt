package com.rochapires.coffeebrewer.features.feature_recipe.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
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
        val methodIdKey = intPreferencesKey(Constants.METHOD_ID_KEY)
        val recipeIdKey = intPreferencesKey(Constants.RECIPE_ID_KEY)
        val defaultCoffeeQuantityKey = doublePreferencesKey(Constants.DEFAULT_COFFEE_QUANTITY_KEY)
        val onboardingDoneKey = booleanPreferencesKey(Constants.ONBOARDING_DONE_KEY)
    }

    override suspend fun getDefaultMethod(): Int? {
        return read(methodIdKey)
    }

    override suspend fun setDefaultMethod(methodId: Int) {
        save(methodIdKey, methodId)
    }

    override suspend fun getDefaultRecipe(): Int? {
        return read(recipeIdKey)
    }

    override suspend fun setDefaultRecipe(recipeId: Int) {
        save(recipeIdKey, recipeId)
    }

    override suspend fun getDefaultCoffeeQuantity(): Double? {
        return read(defaultCoffeeQuantityKey)
    }

    override suspend fun setDefaultCoffeeQuantity(coffeeQuantity: Double) {
        save(defaultCoffeeQuantityKey, coffeeQuantity)
    }

    override suspend fun getOnboardingDone(): Boolean {
        return read(onboardingDoneKey) ?: false
    }

    override suspend fun setOnboardingDone(done: Boolean) {
        save(onboardingDoneKey, done)
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