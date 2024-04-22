package cz.drekorian.android.flickr.flickr.api.domain

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Photo(
    val title: String,
    val link: String,
    val media: Map<String, String>,
    @JsonNames("date_taken") val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    val authorId: String,
    val tags: List<String>,
)
