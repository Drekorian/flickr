package cz.drekorian.android.flickr.flickr.api.domain

data class PhotoInfo(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Photo>
) {
    companion object {
        val empty: PhotoInfo
            get() = PhotoInfo(
                title = "",
                link = "",
                description = "",
                modified = "",
                generator = "",
                items = emptyList(),
            )
    }
}
