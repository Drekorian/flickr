package cz.drekorian.android.flickr.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import cz.drekorian.android.flickr.ui.FlickrApp
import cz.drekorian.android.flickr.ui.theme.FlickrTheme

class MainActivity :
    ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrTheme {
                Box(
                    modifier = Modifier.safeDrawingPadding(),
                ) {
                    FlickrApp()
                }
            }
        }
    }
}
