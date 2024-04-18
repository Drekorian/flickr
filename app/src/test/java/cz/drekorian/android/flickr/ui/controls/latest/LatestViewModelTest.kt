package cz.drekorian.android.flickr.ui.controls.latest

import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class LatestViewModelTest {

    private val mockPhotoInfo: PhotoInfo = mockk()
    private val testScope: TestScope = TestScope()

    // class under test
    private val flickrRepository: IFlickrRepository = mockk(relaxed = true)

    private val settingsLocalDataSource: SettingsLocalDataSource = mockk {
        every { displayMode } returns flowOf(DisplayMode.Grid)
    }

    private val getLatestPhotosUseCase: IGetLatestPhotosUseCase = mockk {
        coEvery { this@mockk.invoke() } returns mockPhotoInfo
    }

    private val viewModel: LatestViewModel = LatestViewModel(
        flickrRepository = flickrRepository,
        settingsLocalDataSource = settingsLocalDataSource,
        getLatestPhotosUseCase = getLatestPhotosUseCase,
    )

    @Test
    fun `when fetch() called then photos are emitted`() = runBlockingTest {

        // given
        junit.framework.Assert.assertTrue(viewModel.photos.value == PhotoInfo.empty)

        // when
        viewModel.refresh()
        testScope.runCurrent()

        // then
        junit.framework.Assert.assertTrue(viewModel.photos.value == mockPhotoInfo)
    }

    private fun runBlockingTest(
        body: suspend TestScope.() -> Unit,
    ): TestResult = testScope.runTest {
        body()
    }
}
