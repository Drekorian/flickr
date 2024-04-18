package cz.drekorian.android.flickr.ui.controls.latest

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.drekorian.android.flickr.R
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.ui.Actions
import cz.drekorian.android.flickr.ui.controls.BottomBar
import cz.drekorian.android.flickr.ui.controls.PhotosGrid
import cz.drekorian.android.flickr.ui.controls.PhotosList
import cz.drekorian.android.flickr.ui.controls.TopAppBar
import cz.drekorian.android.flickr.ui.plus
import org.koin.androidx.compose.getViewModel

@Composable
internal fun LatestScreen(
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    LatestScreen(
        viewModel = getViewModel(),
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
        val isRefreshing by remember(viewModel) { viewModel.isRefreshing }.collectAsState()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = viewModel::refresh,
            indicatorPadding = contentPadding,
        ) {
            val photoInfo by remember { viewModel.photos }.collectAsState()
            val displayMode by remember { viewModel.displayMode }.collectAsState(initial = null)

            val currentDisplayMode = displayMode
            if (currentDisplayMode != null) {
                LatestScreenContent(
                    displayMode = currentDisplayMode,
                    photoInfo = photoInfo,
                    contentPadding = contentPadding,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }
}

@Composable
private fun LatestScreenContent(
    displayMode: DisplayMode,
    photoInfo: PhotoInfo,
    contentPadding: PaddingValues,
) {
    Crossfade(
        targetState = displayMode
    ) { mode ->
        val adjustedContentPadding = contentPadding + PaddingValues(16.dp)
        when (mode) {
            DisplayMode.Grid -> PhotosGrid(
                photos = photoInfo.items,
                modifier = Modifier.fillMaxSize(),
                contentPadding = adjustedContentPadding,
            )

            DisplayMode.List -> PhotosList(
                photos = photoInfo.items,
                modifier = Modifier.fillMaxSize(),
                contentPadding = adjustedContentPadding,
            )
        }
    }
}
