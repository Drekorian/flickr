package cz.drekorian.android.flickr.ui.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import cz.drekorian.android.flickr.R
import cz.drekorian.android.flickr.ui.Actions
import cz.drekorian.android.flickr.ui.Destinations

@Composable
internal fun BottomBar(
    actions: Actions,
    navBackStackEntry: NavBackStackEntry,
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .height(80.dp)
            .clipToBounds()
    ) {
        Action(
            selected = navBackStackEntry.destination.route == Destinations.Latest,
            title = stringResource(R.string.screen_latest),
            onClick = actions::showLatest,
        )
        Action(
            selected = navBackStackEntry.destination.route == Destinations.Search,
            title = stringResource(R.string.screen_search),
            onClick = actions::showSearch,
        )
    }
}

@Composable
private fun RowScope.Action(
    selected: Boolean,
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxHeight()
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
            textAlign = TextAlign.Center,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
