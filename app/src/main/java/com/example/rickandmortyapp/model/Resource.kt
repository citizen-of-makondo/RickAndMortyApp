package com.example.rickandmortyapp.model

data class Resource<out T>(val status: CharacterLoadStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = CharacterLoadStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = CharacterLoadStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = CharacterLoadStatus.LOADING, data = data, message = null)
    }
}
