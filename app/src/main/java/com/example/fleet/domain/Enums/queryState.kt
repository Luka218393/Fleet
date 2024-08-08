package com.example.fleet.domain.Enums

sealed class  QueryState {
    data object Loading : QueryState()
    data class Success(val data: String) : QueryState()
    data class Error(val message: String) : QueryState()
}