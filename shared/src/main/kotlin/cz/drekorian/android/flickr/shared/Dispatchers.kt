package cz.drekorian.android.flickr.shared

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface Dispatchers {
    val main: CoroutineContext
    val mainImmediate: CoroutineContext
    val default: CoroutineContext
    val io: CoroutineContext

    val appLifecycleOwner: LifecycleOwner
}

@SuppressLint("DispatchersUsage")
class DefaultDispatchers : Dispatchers {
    override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val mainImmediate: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main.immediate
    override val default: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
    override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO

    override val appLifecycleOwner: LifecycleOwner = ProcessLifecycleOwner.get()
}
