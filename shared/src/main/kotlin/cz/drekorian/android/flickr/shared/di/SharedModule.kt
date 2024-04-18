package cz.drekorian.android.flickr.shared.di

import cz.drekorian.android.flickr.shared.DefaultDispatchers
import cz.drekorian.android.flickr.shared.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val sharedModule = module {
    single<Dispatchers> { DefaultDispatchers() }
    single { CoroutineScope(SupervisorJob() + get<Dispatchers>().default) }
}
