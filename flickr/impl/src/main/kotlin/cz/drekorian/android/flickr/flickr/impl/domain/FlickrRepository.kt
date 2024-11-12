package cz.drekorian.android.flickr.flickr.impl.domain

import cz.drekorian.android.flickr.flickr.api.IFlickrRepository
import cz.drekorian.android.flickr.flickr.api.Result
import cz.drekorian.android.flickr.flickr.api.domain.PhotoInfo
import cz.drekorian.android.flickr.flickr.api.domain.TagMode
import cz.drekorian.android.flickr.flickr.impl.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

internal class FlickrRepository(
    private val client: KtorClient,
    private val mapper: PhotoMapper,
) :
    IFlickrRepository {

    companion object {

        private const val PARAMETER_NAME_FORMAT = "format"

        @SuppressWarnings("SpellCheckingInspection")
        private const val PARAMETER_NAME_TAG_MODE = "tagmode"

        @SuppressWarnings("SpellCheckingInspection")
        private const val PARAMETER_NAME_TAGS = "tags"

        @SuppressWarnings("SpellCheckingInspection")
        private const val PARAMETER_NAME_NO_JSON_CALLBACK = "nojsoncallback"
        private const val PARAMETER_VALUE_NO_JSON_CALLBACK_TRUE = "1"
    }

    override suspend fun fetch(
        tags: List<String>,
        tagMode: TagMode,
    ): Result<PhotoInfo> {
        val response = client.httpClient.get {
            url {
                appendPathSegments("services", "feeds", "photos_public.gne")
            }
            parameter(PARAMETER_NAME_FORMAT, Format.Json.value)
            parameter(
                PARAMETER_NAME_NO_JSON_CALLBACK,
                PARAMETER_VALUE_NO_JSON_CALLBACK_TRUE
            )
            parameter(PARAMETER_NAME_TAG_MODE, tagMode.value)
            parameter(PARAMETER_NAME_TAGS, tags.joinToString(separator = ","))
        }

        return Result.Success(mapper.from(response.body()))
    }
}
