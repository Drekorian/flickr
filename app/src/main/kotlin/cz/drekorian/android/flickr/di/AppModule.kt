package cz.drekorian.android.flickr.di

import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.ui.controls.TopAppBarViewModel
import cz.drekorian.android.flickr.ui.controls.latest.LatestViewModel
import cz.drekorian.android.flickr.ui.controls.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { SettingsLocalDataSource(get()) }

    viewModel { LatestViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { TopAppBarViewModel(get()) }
}
