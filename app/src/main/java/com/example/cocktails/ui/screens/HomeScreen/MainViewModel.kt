package com.example.cocktails.ui.screens.HomeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
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
    val isLoading:  MutableState<Boolean>  = mutableStateOf(false)
    val isError: MutableState<Boolean> = mutableStateOf(false)
    val drinksList: MutableState<List<DrinkItem>?> = mutableStateOf(null)

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
              when(it){
                 is DataState.Loading->{
                      isError.value = false
                      isLoading.value = true
                  }
                  is DataState.Success -> {
                      isLoading.value = false
                      isError.value = false
                      drinksList.value = it.data
                  }
                  is DataState.Error -> {
                      isError.value = true
                  }
              }
            }

        }
    }

}