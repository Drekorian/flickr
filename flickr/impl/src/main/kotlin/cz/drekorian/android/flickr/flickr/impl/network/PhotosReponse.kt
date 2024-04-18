package cz.drekorian.android.flickr.flickr.impl.network

import kotlinx.serialization.Serializable

@Serializable
internal data class PhotosResponse(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Photo>
)
