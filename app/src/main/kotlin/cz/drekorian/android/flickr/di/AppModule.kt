package cz.drekorian.android.flickr.di

import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.ui.controls.TopAppBarViewModel
import cz.drekorian.android.flickr.ui.controls.latest.LatestViewModel
import cz.drekorian.android.flickr.ui.controls.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single { SettingsLocalDataSource(get()) }

    viewModelOf(::LatestViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::TopAppBarViewModel)
}
