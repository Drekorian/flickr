package cz.drekorian.android.flickr.di

import cz.drekorian.android.flickr.flickr.impl.di.flickrModule
import cz.drekorian.android.flickr.shared.di.sharedModule

val modules = listOf(
    appModule,
    flickrModule,
    sharedModule,
)
