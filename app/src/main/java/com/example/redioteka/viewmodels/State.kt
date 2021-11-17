package com.example.redioteka.viewmodels

sealed interface State<T> {
    data class Success<T>(var data: T): State<T>
    data class Fail<T>(var error: Throwable?): State<T>
}