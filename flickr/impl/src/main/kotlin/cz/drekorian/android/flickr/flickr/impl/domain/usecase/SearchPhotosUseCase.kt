package cz.drekorian.android.flickr.flickr.impl.domain.usecase

import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.TagMode
import cz.drekorian.android.flickr.flickr.api.domain.usecase.ISearchPhotosUseCase

internal class SearchPhotosUseCase(
    private val flickrRepository: IFlickrRepository
) : ISearchPhotosUseCase {

    override suspend operator fun invoke(
        tags: List<String>,
        tagMode: TagMode,
    ): Result<PhotoInfo> {
        return flickrRepository.fetch(
            tags = tags,
            tagMode = tagMode
        )
    }
}
