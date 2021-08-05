package com.example.rickandmortyapp.model

data class Resource<out T>(val status: LoadStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = LoadStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = LoadStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = LoadStatus.LOADING, data = data, message = null)
    }
}

enum class LoadStatus {
    SUCCESS,
    ERROR,
    LOADING
}