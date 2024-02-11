package com.example.cocktails.data.repository

import com.example.cocktails.data.model.DrinkResponseModel

interface DrinksRepository {
    suspend fun drinksList(text: String):  Result<DrinkResponseModel>
}
