package cz.drekorian.android.flickr

import android.app.Application
import coil.Coil
import coil.ImageLoader
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import cz.drekorian.android.flickr.di.modules as diModules

class FlickrApplication :
    Application() {

    companion object {

        @Suppress("SpellCheckingInspection")
        private const val USER_AGENT =
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.5195.79 Mobile Safari/537.36"
    }

    private lateinit var koinApplication: KoinApplication

    override fun onCreate() {
        super.onCreate()

        koinApplication = startKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@FlickrApplication)
            modules(diModules)
        }

        Coil.setImageLoader(
            ImageLoader.Builder(this)
                .okHttpClient {
                    OkHttpClient.Builder()
                        .addNetworkInterceptor { chain ->
                            val modifiedRequest = chain.request().newBuilder()
                                .header("User-Agent", USER_AGENT)
                                .build()

                            chain.proceed(modifiedRequest)
                        }
                        .build()
                }
                .build()
        )
    }
}
