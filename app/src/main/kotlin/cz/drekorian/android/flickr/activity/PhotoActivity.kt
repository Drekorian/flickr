package cz.drekorian.android.flickr.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.drekorian.android.flickr.flickr.api.domain.Photo
import cz.drekorian.android.flickr.ui.controls.photo.PhotoScreen
import cz.drekorian.android.flickr.ui.theme.FlickrTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PhotoActivity:
    ComponentActivity() {

    companion object {

        const val EXTRA_KEY_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val json = intent.extras?.getString(EXTRA_KEY_DATA) ?: ""
            val photo = Json.decodeFromString<Photo>(json)

            FlickrTheme {
                PhotoScreen(photo)
            }
        }
    }
}