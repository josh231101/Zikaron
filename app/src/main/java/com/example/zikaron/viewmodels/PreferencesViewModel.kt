package com.example.zikaron.viewmodels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesViewModel(val context: Context): ViewModel() {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val NAME = stringPreferencesKey("name")
        val IS_AUTH = booleanPreferencesKey("isAuth")
    }

    //getName() asincrono
    val name: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[NAME] ?: ""
        }
    //getHobby() asincrono
    val isAuth: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_AUTH] ?: false
        }

    //setName & setAge
    suspend fun setUserAuthenticated(name: String, isAuth: Boolean) {
        context.dataStore.edit { settings ->
            settings[NAME] = name
            settings[IS_AUTH] = isAuth
        }
    }
}