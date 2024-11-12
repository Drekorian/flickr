package cz.drekorian.android.flickr.ui.controls.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import cz.drekorian.android.flickr.R
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.ui.Actions
import cz.drekorian.android.flickr.ui.controls.BottomBar
import cz.drekorian.android.flickr.ui.controls.PhotosGrid
import cz.drekorian.android.flickr.ui.controls.PhotosList
import cz.drekorian.android.flickr.ui.controls.TopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SearchScreen(
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    SearchScreen(
        viewModel = koinViewModel(),
        actions = actions,
        navBackStackEntry = navBackStackEntry,
    )
}

@Composable
private fun SearchScreen(
    viewModel: SearchViewModel,
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    Scaffold(
        topBar = { TopAppBar(stringResource(R.string.screen_search)) },
        bottomBar = {
            BottomBar(
                actions = actions,
                navBackStackEntry = navBackStackEntry,
            )
        }
    ) { contentPadding ->
        val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = viewModel::refresh,
            modifier = Modifier.padding(contentPadding),
        ) {
            val displayMode by viewModel.displayMode.collectAsStateWithLifecycle(initialValue = null)
            val photoInfo by viewModel.photos.collectAsStateWithLifecycle()
            val searchTerm by viewModel.searchTerm.collectAsStateWithLifecycle()

            val currentDisplayMode = displayMode
            if (currentDisplayMode != null) {
                SearchScreenContent(
                    displayMode = currentDisplayMode,
                    photoInfo = photoInfo,
                    searchTerm = searchTerm,
                    onSearchTermChange = { value -> viewModel.onSearchTermChange(value) }
                )
            }
        }
    }
}

@Composable
private fun SearchScreenContent(
    displayMode: DisplayMode,
    photoInfo: PhotoInfo,
    searchTerm: String,
    onSearchTermChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SearchField(
            searchTerm = searchTerm,
            onSearchTermChange = onSearchTermChange,
        )

        Crossfade(
            targetState = displayMode,
            label = "displayMode",
        ) { mode ->
            when (mode) {
                DisplayMode.Grid -> PhotosGrid(
                    photos = photoInfo.items,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                )

                DisplayMode.List -> PhotosList(
                    photos = photoInfo.items,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                )
            }
        }
    }
}

@Composable
private fun SearchField(
    searchTerm: String,
    onSearchTermChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        TextField(
            value = searchTerm,
            onValueChange = onSearchTermChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.action_search),
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                )
            },
            maxLines = 1,
        )
    }
}
