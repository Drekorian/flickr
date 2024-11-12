package cz.drekorian.android.flickr.ui.controls.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.flickr.api.Result.Success
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.ISearchPhotosUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(
    settingsLocalDataSource: SettingsLocalDataSource,
    private val searchPhotosUseCase: ISearchPhotosUseCase,
) :
    ViewModel() {

    val displayMode: Flow<DisplayMode> = settingsLocalDataSource.displayMode

    private val _searchTerm = MutableStateFlow("")
    val searchTerm = _searchTerm.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _photos = MutableStateFlow(PhotoInfo.empty)
    val photos = _photos.asStateFlow()

    init {
        _searchTerm
            .debounce(300)
            .onEach { fetch() }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetch()
            _isRefreshing.value = false
        }
    }

    suspend fun fetch() {
        val result = searchPhotosUseCase(
            tags = _searchTerm.value.trimEnd().split(" ")
        )

        if (result is Success) {
            _photos.value = result.value
        }
    }

    fun onSearchTermChange(value: String) {
        _searchTerm.value = value
    }
}
