package cz.drekorian.android.flickr.flickr.impl.di

import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase
import cz.drekorian.android.flickr.flickr.api.domain.usecase.ISearchPhotosUseCase
import cz.drekorian.android.flickr.flickr.impl.domain.FlickrRepository
import cz.drekorian.android.flickr.flickr.impl.domain.PhotoMapper
import cz.drekorian.android.flickr.flickr.impl.domain.usecase.GetLatestPhotosUseCase
import cz.drekorian.android.flickr.flickr.impl.domain.usecase.SearchPhotosUseCase
import cz.drekorian.android.flickr.flickr.impl.network.HttpLogger
import cz.drekorian.android.flickr.flickr.impl.network.KtorClient
import org.koin.dsl.module

val flickrModule = module {
    factory { HttpLogger() }
    factory<IGetLatestPhotosUseCase> { GetLatestPhotosUseCase(get()) }
    factory<ISearchPhotosUseCase> { SearchPhotosUseCase(get()) }
    factory { PhotoMapper() }

    single { KtorClient(get()) }
    factory<IFlickrRepository> { FlickrRepository(get(), get()) }
}
