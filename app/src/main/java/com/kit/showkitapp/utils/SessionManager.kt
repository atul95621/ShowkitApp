package com.legal.smart.util;

import android.content.Context
import androidx.datastore.preferences.*
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

    }

}