package com.example.redioteka.repository

sealed interface Result<T> {
    data class Success<T>(var data: T): Result<T>
    data class Fail<T>(var error: Throwable?): Result<T>
}