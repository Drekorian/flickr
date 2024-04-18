package cz.drekorian.android.flickr.ui.controls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TopAppBarViewModel(
    private val settingsLocalDataSource: SettingsLocalDataSource,
) :
    ViewModel() {

    val displayMode: Flow<DisplayMode> = settingsLocalDataSource.displayMode

    fun toggleDisplayMode() {
        viewModelScope.launch {
            val currentDataMode = displayMode.first()
            settingsLocalDataSource.setDisplayMode(
                when (currentDataMode) {
                    DisplayMode.Grid -> DisplayMode.List
                    DisplayMode.List -> DisplayMode.Grid
                }
            )
        }
    }
}
