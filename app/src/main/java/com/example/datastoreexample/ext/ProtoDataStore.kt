package com.example.datastoreexample.ext

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ProtoDataStore(private val context: Context) {
    suspend fun setEmail(email: String) {
        context.ProtoDataStore.updateData { preferences ->
            preferences.toBuilder().setEmail(email).build()
        }
    }

    val protoFlow: Flow<String> = context.ProtoDataStore.data
        .catch { it ->
            if (it is IOException) {
                Log.e("Tag", "Error reading sort order preferences.", it)

            }
        }.map {
            it.email
        }

    suspend fun clearData() {
        context.ProtoDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }

}
