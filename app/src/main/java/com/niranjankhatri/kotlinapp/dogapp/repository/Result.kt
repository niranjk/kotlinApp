package com.niranjankhatri.kotlinapp.dogapp.repository

sealed class Result<T>{
    class Loading<T> : Result<T>()
    data class Success<T>(val data: T): Result<T>()
    class Error<T> : Result<T>()
}
