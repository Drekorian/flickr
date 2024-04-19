package cz.drekorian.android.flickr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

internal abstract class BaseTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testScope: TestScope = TestScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(testScope.testScheduler)

    @get:Rule
    val coroutinesTestRule: CoroutineTestRule = CoroutineTestRule(testDispatcher)
}
