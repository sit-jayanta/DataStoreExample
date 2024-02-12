package com.example.datastoreexample.ext.splash

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.datastoreexample.ext.PreferenceKey.IS_LOGGEDIN
import com.example.datastoreexample.ext.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class UserPreferences(private var context: Context) {

    suspend fun setBoolean(status: Boolean, key: Preferences.Key<Boolean>) {
        context.dataStore.edit {
            it[key] = status
            // here it refers to the preferences we are editing
        }
    }

    suspend fun setString(status: String, key: Preferences.Key<String>) {
        context.dataStore.edit {
            it[key] = status
            // here it refers to the preferences we are editing
        }
    }

    fun getBooleanData(key: Preferences.Key<Boolean>): Flow<Boolean> {
        val data: Flow<Boolean> = context.dataStore.data
            .catch {
                if (it is IOException) {
                    Log.d("tag", it.toString())
                } else {
                    throw it
                }
            }.map {
                it[IS_LOGGEDIN] ?: false
            }
        return data
    }

    fun getStringData(key: Preferences.Key<String>): Flow<String> {
        val data: Flow<String> = context.dataStore.data
            .catch {
                if (it is IOException) {
                    Log.d("tag", it.toString())
                } else {
                    throw it
                }
            }.map {
                it[key] ?: " "
            }
        return data
    }

    suspend fun clearPreference(){
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
