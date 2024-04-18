package cz.drekorian.android.flickr.flickr.impl.network

import android.util.Log
import io.ktor.client.plugins.logging.Logger

internal class HttpLogger : Logger {
    companion object {

        private const val TAG = "Ktor"
    }

    override fun log(message: String) {
        Log.d(TAG, message)
    }
}
