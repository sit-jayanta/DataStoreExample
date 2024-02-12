package com.example.datastoreexample.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastoreexample.ProtoExample
import com.example.datastoreexample.ext.AppConstans.Companion.DATA_STORE_FILE_NAME
import com.example.datastoreexample.ext.AppConstans.Companion.SHARED


val Context.dataStore by preferencesDataStore(
    name = SHARED
)

val Context.ProtoDataStore: DataStore<ProtoExample> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = ProtoSerializer
)
