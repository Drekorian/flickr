package cz.drekorian.android.flickr.domain

enum class DisplayMode(val value: Int) {
    Grid(0),
    List(1);

    companion object {

        fun from(value: Int): DisplayMode = when (value) {
            1 -> List
            else -> Grid
        }
    }
}
