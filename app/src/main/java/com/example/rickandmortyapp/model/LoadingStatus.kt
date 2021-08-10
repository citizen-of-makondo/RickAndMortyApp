package com.example.rickandmortyapp.model


data class LoadingStatus<out T>(val statusEnum: LoadStatusEnum, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): LoadingStatus<T> =
            LoadingStatus(statusEnum = LoadStatusEnum.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): LoadingStatus<T> =
            LoadingStatus(statusEnum = LoadStatusEnum.ERROR, data = data, message = message)
    }
}

enum class LoadStatusEnum {
    SUCCESS,
    ERROR,
}