package cz.drekorian.android.flickr.domain

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

class SettingsLocalDataSource(
    private val context: Context,
) {

    companion object {

        private const val DATA_STORE_NAME = "settings"

        private object Keys {

            val DisplayMode = intPreferencesKey("pref_display_mode")
        }
    }

    private val Context.dataStore by preferencesDataStore(
        name = DATA_STORE_NAME,
        produceMigrations = { emptyList() },
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
    )

    val displayMode: Flow<DisplayMode> =
        context.dataStore.preference(
            key = Keys.DisplayMode,
            defaultValue = DisplayMode.Grid,
            transform = { value -> DisplayMode.from(value) }
        )

    suspend fun setDisplayMode(displayMode: DisplayMode) {
        context.dataStore.setPreference(
            key = Keys.DisplayMode,
            value = displayMode.value,
        )
    }
}
