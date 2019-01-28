package com.andre.test.features

sealed class UiState {
    object Loading : UiState()
    object Error : UiState()
    object Empty : UiState()
    object Sucess : UiState()
}