package cz.drekorian.android.flickr.ui.controls

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.drekorian.android.flickr.R
import cz.drekorian.android.flickr.flickr.api.domain.Photo

@Composable
internal fun Photo.getProperPhotoTitle(): String = when {
    title.isBlank() -> stringResource(R.string.untitled)
    else -> title
}
