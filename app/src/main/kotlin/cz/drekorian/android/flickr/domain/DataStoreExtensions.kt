package cz.drekorian.android.flickr.domain

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException

internal fun <T : Any, U : Any?> DataStore<Preferences>.preference(
    key: Preferences.Key<T>,
    defaultValue: U,
    transform: (T) -> U?,
): Flow<U> = getPreferences()
    .map { preferences -> preferences[key]?.let { transform(it) } ?: defaultValue }
    .distinctUntilChanged()

internal fun DataStore<Preferences>.getPreferences(): Flow<Preferences> =
    this.data.catch { exception ->
        if (exception is IOException) {
            Log.e("DataStore", "Reading data has failed", exception)
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }

internal suspend fun <T : Any> DataStore<Preferences>.setPreference(
    key: Preferences.Key<T>,
    value: T?,
) {
    runCatching {
        this.edit { preferences ->
            when (value) {
                null -> preferences.remove(key)
                else -> preferences[key] = value
            }
        }
    }
}
