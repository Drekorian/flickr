package cz.drekorian.android.flickr.ui.controls.latest

import cz.drekorian.android.flickr.BaseTest
import cz.drekorian.android.flickr.domain.DisplayMode
import cz.drekorian.android.flickr.domain.SettingsLocalDataSource
import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class LatestViewModelTest :
    BaseTest() {

    private val mockPhotoInfo: PhotoInfo = mockk()

    private val settingsLocalDataSource: SettingsLocalDataSource = mockk {
        every { displayMode } returns flowOf(DisplayMode.Grid)
    }

    private val getLatestPhotosUseCase: IGetLatestPhotosUseCase = mockk {
        coEvery { this@mockk.invoke() } returns Result.Success(mockPhotoInfo)
    }

    // class under test
    private val viewModel: LatestViewModel = LatestViewModel(
        settingsLocalDataSource = settingsLocalDataSource,
        getLatestPhotosUseCase = getLatestPhotosUseCase,
    )

    @Test
    fun `when fetch() called then photos are emitted`() = runTest {

        // given
        assertEquals(viewModel.photos.value, PhotoInfo.empty)

        // when
        viewModel.refresh()
        runCurrent()

        // then
        assertEquals(viewModel.photos.value, mockPhotoInfo)
    }
}
