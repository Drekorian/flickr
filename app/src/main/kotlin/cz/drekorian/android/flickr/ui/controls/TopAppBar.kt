package cz.drekorian.android.flickr.ui.controls

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.drekorian.android.flickr.R
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.ui.theme.FlickrTheme
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        viewModel = koinViewModel(),
        title = title,
        modifier = modifier,
    )
}

@Composable
private fun TopAppBar(
    viewModel: TopAppBarViewModel,
    title: String,
    modifier: Modifier,
) {
    val displayMode by viewModel.displayMode.collectAsStateWithLifecycle(initialValue = null)
    TopAppBar(
        displayMode = displayMode,
        title = title,
        modifier = modifier,
        onActionClick = viewModel::toggleDisplayMode,
    )
}

@Composable
private fun TopAppBar(
    displayMode: DisplayMode?,
    title: String,
    modifier: Modifier,
    onActionClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (displayMode != null) {
                IconButton(
                    onClick = onActionClick,
                ) {
                    Icon(
                        imageVector = when (displayMode) {
                            DisplayMode.Grid -> ImageVector.vectorResource(R.drawable.apps)
                            DisplayMode.List -> Icons.Default.Menu
                        },
                        contentDescription = null,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun TopAppBarLatestPreview() {
    FlickrTheme {
        TopAppBar(
            displayMode = DisplayMode.Grid,
            title = stringResource(R.string.screen_latest),
            modifier = Modifier.fillMaxWidth(),
            onActionClick = { },
        )
    }
}

@Preview
@Composable
private fun TopAppBarSearchPreview() {
    FlickrTheme {
        TopAppBar(
            displayMode = DisplayMode.List,
            title = stringResource(R.string.screen_search),
            modifier = Modifier.fillMaxWidth(),
            onActionClick = { },
        )
    }
}
