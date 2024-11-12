package cz.drekorian.android.flickr.ui.controls.latest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.flickr.api.Result.Success
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LatestViewModel(
    settingsLocalDataSource: SettingsLocalDataSource,
    private val getLatestPhotosUseCase: IGetLatestPhotosUseCase,
) :
    ViewModel() {

    val displayMode: Flow<DisplayMode> = settingsLocalDataSource.displayMode
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _photos = MutableStateFlow(PhotoInfo.empty)
    val photos: StateFlow<PhotoInfo> = _photos.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            val result = getLatestPhotosUseCase()
            if (result is Success) {
                _photos.value = result.value
            }
            _isRefreshing.value = false
        }
    }
}
