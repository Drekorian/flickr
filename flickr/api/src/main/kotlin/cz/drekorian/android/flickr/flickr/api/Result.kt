package cz.drekorian.android.flickr.flickr.api

sealed interface Result<T> {

    class Error<T>(val errorMessage: String, throwable: Throwable?) : Result<T>
    class Success<T>(val value: T) : Result<T>
}
