package com.andre.test.features

sealed class UiState {
    object Loading : UiState()
    data class Error(val errorId: Int) : UiState()
    object Empty : UiState()
    object Success : UiState()
    object Initial : UiState()
}