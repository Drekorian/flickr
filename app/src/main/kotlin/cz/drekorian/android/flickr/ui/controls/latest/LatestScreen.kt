package cz.drekorian.android.flickr.ui.controls.latest

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
internal fun LatestScreen(
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    LatestScreen(
        viewModel = koinViewModel(),
        actions = actions,
        navBackStackEntry = navBackStackEntry,
    )
}

@Composable
internal fun LatestScreen(
    viewModel: LatestViewModel,
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    Scaffold(
        topBar = { TopAppBar(stringResource(R.string.screen_latest)) },
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
            state = rememberPullToRefreshState(),
            modifier = Modifier.padding(contentPadding),
        ) {
            val photoInfo by viewModel.photos.collectAsStateWithLifecycle()
            val displayMode by viewModel.displayMode.collectAsStateWithLifecycle(initialValue = null)

            val currentDisplayMode = displayMode
            if (currentDisplayMode != null) {
                LatestScreenContent(
                    displayMode = currentDisplayMode,
                    photoInfo = photoInfo,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetch()
    }
}

@Composable
private fun LatestScreenContent(
    displayMode: DisplayMode,
    photoInfo: PhotoInfo,
) {
    Crossfade(
        targetState = displayMode,
        label = "displayMode",
    ) { mode ->
        when (mode) {
            DisplayMode.Grid -> PhotosGrid(
                photos = photoInfo.items,
                modifier = Modifier.fillMaxSize(),
            )

            DisplayMode.List -> PhotosList(
                photos = photoInfo.items,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
