package cz.drekorian.android.flickr.flickr.api.domain.usecase

import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.TagMode

interface ISearchPhotosUseCase {

    suspend operator fun invoke(
        tags: List<String>,
        tagMode: TagMode = TagMode.All,
    ): Result<PhotoInfo>
}
