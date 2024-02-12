package com.example.datastoreexample.ext

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

 object PreferenceKey {
    val NAME = stringPreferencesKey("name")
    val PASSWORD = stringPreferencesKey("password")
    var IS_LOGGEDIN = booleanPreferencesKey("isLoggedIn")
}