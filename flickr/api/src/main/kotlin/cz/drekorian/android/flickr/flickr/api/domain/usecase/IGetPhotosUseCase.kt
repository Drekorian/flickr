package cz.drekorian.android.flickr.flickr.api.domain.usecase

import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo

interface IGetLatestPhotosUseCase {

    suspend operator fun invoke(): Result<PhotoInfo>
}
