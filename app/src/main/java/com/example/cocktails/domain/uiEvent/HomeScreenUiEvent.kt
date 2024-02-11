package com.example.cocktails.domain.uiEvent

sealed class HomeScreenUiEvent {
    data class SearchQueryTyped(val value: String) : HomeScreenUiEvent()
}