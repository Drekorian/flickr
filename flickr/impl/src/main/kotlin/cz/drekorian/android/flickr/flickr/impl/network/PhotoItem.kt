package cz.drekorian.android.flickr.flickr.impl.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
internal class Photo(
    val title: String,
    val link: String,
    val media: Map<String, String>,
    @JsonNames("date_taken") val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    @JsonNames("author_id") val authorId: String,
    val tags: String,
)

