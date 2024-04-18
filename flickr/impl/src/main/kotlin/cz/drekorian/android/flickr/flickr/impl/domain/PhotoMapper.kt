package cz.drekorian.android.flickr.flickr.impl.domain

import cz.drekorian.android.flickr.flickr.api.domain.Photo
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.impl.network.PhotosResponse
import cz.drekorian.android.flickr.flickr.impl.network.Photo as PhotoNetwork

internal class PhotoMapper {

    fun from(response: PhotosResponse) = PhotoInfo(
        title = response.title,
        link = response.link,
        description = response.description,
        modified = response.modified,
        generator = response.generator,
        items = response.items.map { photoItem -> from(photoItem) }
    )

    private fun from(photoNetwork: PhotoNetwork) = Photo(
        title = photoNetwork.title,
        link = photoNetwork.link,
        media = photoNetwork.media,
        description = photoNetwork.description,
        published = photoNetwork.published,
        author = photoNetwork.published,
        authorId = photoNetwork.authorId,
        tags = photoNetwork.tags.split(" "),
    )
}
