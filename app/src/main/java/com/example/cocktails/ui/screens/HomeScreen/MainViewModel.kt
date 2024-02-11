package com.example.cocktails.ui.screens.HomeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.data.model.DrinkItem
import com.example.cocktails.domain.uiEvent.HomeScreenUiEvent
import com.example.cocktails.domain.usecases.SearchDrinkUseCase
import com.example.cocktails.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: SearchDrinkUseCase
) : ViewModel() {

    private var fetchDrinkJob: Job? = null
    val drinksList: MutableState<DataState<List<DrinkItem>>?> = mutableStateOf(null)

    init {
        fetchDrinks()
    }

    fun uiEvent(event: HomeScreenUiEvent) {
        when(event){
            is HomeScreenUiEvent.SearchQueryTyped -> {
                fetchDrinks(event.value)
            }
        }
    }

    private fun fetchDrinks(text: String="") {
        fetchDrinkJob?.cancel()
        fetchDrinkJob = viewModelScope.launch {
            delay(200)
            repo.execute(text).collectLatest {
              drinksList.value = it
            }

        }
    }

}