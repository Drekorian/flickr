package cz.drekorian.android.flickr.ui.controls

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.parseAsHtml
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import cz.drekorian.android.flickr.activity.PhotoActivity
import cz.drekorian.android.flickr.flickr.api.domain.Photo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
internal fun PhotosList(
    photos: List<Photo>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
) {
    LazyColumn(
        verticalArrangement = verticalArrangement,
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        for (photo in photos) {
            item(photo.link) {
                val context = LocalContext.current

                Card {
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primary)
                            .fillMaxSize()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        PhotoActivity::class.java,
                                    ).putExtra(
                                        PhotoActivity.EXTRA_KEY_DATA, Json.encodeToString(photo)
                                    )
                                )
                            }
                            .animateItem(),
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(photo.media.values.first())
                                    .crossfade(true)
                                    .build(),
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = photo.description.parseAsHtml().toString(),
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .fillMaxSize()
                                .aspectRatio(1f),
                        )

                        Text(
                            text = photo.getProperPhotoTitle(),
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                                .padding(16.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontStyle = if (photo.title.isBlank()) {
                                    FontStyle.Italic
                                } else {
                                    FontStyle.Normal
                                }
                            ),
                        )
                    }
                }
            }
        }
    }
}
