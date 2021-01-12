package com.kit.showkitapp.utils;

import android.content.Context
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SessionManager(context: Context) {
    private val preferenceDataStore = context.createDataStore(name = "data_store_pref")

    suspend fun getData(
        key: Preferences.Key<String>
    ): Flow<String> {

        val flowObserve: Flow<String> = preferenceDataStore.data
            // catch will catch the exception if any
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            // map will give us the value
            .map { preference ->
                // "" will be passed if its null
                preference[key] ?: ""
            }

        return flowObserve
    }

    suspend fun setData(key: Preferences.Key<String>, value: String) {
        preferenceDataStore.edit { preferences ->
            preferences[key] = value
        }
    }


    companion object {
        val USER_NAME = preferencesKey<String>("user_name")
        val USER_LOCATION = preferencesKey<String>("user_location")
        val USER_AGE = preferencesKey<String>("user_age")
        val GENDER = preferencesKey<String>("gender")
        val USER_ID = preferencesKey<String>("user_id")
        val ACCESS_TOKEN = preferencesKey<String>("access_token")
        val FIRST_NAME = preferencesKey<String>("first_name")
        val LAST_NAME = preferencesKey<String>("last_name")
        val USER_INTEREST = preferencesKey<String>("user_interest")
        val USER_LANGUAGE = preferencesKey<String>("langugae")
        val IS_LOGGED_IN = preferencesKey<String>("is_logged_in")

    }

}