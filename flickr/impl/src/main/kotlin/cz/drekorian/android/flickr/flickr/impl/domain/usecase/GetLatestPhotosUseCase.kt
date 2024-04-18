package cz.drekorian.android.flickr.flickr.impl.domain.usecase

import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.usecase.IGetLatestPhotosUseCase

internal class GetLatestPhotosUseCase(
    private val flickrRepository: IFlickrRepository,
) : IGetLatestPhotosUseCase {

    override suspend operator fun invoke(): Result<PhotoInfo> {
        return flickrRepository.fetch()
    }
}
