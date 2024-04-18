package cz.drekorian.android.flickr.ui.controls.latest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.Result.Success
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LatestViewModel(
    flickrRepository: IFlickrRepository,
    settingsLocalDataSource: SettingsLocalDataSource,
    private val getLatestPhotosUseCase: IGetLatestPhotosUseCase,
) :
    ViewModel() {

    val displayMode: Flow<DisplayMode> = settingsLocalDataSource.displayMode
    val isRefreshing: StateFlow<Boolean> = flickrRepository.isLoading

    private val _photos = MutableStateFlow(PhotoInfo.empty)
    val photos: StateFlow<PhotoInfo> = _photos.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            val result = getLatestPhotosUseCase()
            if (result is Success) {
                _photos.value = result.value
            }
        }
    }
}
