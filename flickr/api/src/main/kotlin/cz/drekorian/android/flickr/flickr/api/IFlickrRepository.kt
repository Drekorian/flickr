package cz.drekorian.android.flickr.flickr.api

import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.TagMode

interface IFlickrRepository {

    suspend fun fetch(
        tags: List<String> = emptyList(),
        tagMode: TagMode = TagMode.All,
    ): Result<PhotoInfo>
}
