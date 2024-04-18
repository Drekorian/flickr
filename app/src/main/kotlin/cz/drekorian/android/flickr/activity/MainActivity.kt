package cz.drekorian.android.flickr.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.drekorian.android.flickr.ui.FlickrApp
import cz.drekorian.android.flickr.ui.theme.FlickrTheme

class MainActivity :
    ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrTheme {
                FlickrApp()
            }
        }
    }
}
