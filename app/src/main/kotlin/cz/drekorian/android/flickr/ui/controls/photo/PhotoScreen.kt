package cz.drekorian.android.flickr.ui.controls.photo

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.parseAsHtml
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import cz.drekorian.android.flickr.flickr.api.domain.Photo

@Composable
internal fun PhotoScreen(
    photo: Photo,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        var scale by remember { mutableStateOf(1f) }
        val url = photo.media.values.first().replace("_m", "_c")
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
            ),
            contentScale = ContentScale.FillWidth,
            contentDescription = photo.description.parseAsHtml().toString(),
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                )
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, _ ->
                        scale = when {
                            scale < 0.5f -> 0.5f
                            scale > 3f -> 3f
                            else -> scale * zoom
                        }
                    }
                },
        )

        val context = LocalContext.current
        IconButton(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_STREAM, url)
                        type = "image/jpeg"
                    }
                )
            },
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
