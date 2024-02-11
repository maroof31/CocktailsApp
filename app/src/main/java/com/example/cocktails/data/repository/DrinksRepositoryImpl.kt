package com.example.cocktails.data.repository

import com.example.cocktails.data.datasource.remote.DrinksApi
import com.example.cocktails.data.model.DrinkResponseModel
import com.example.cocktails.data.model.DrinksItemDTO
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(
    private val drinksApi: DrinksApi
    ): DrinksRepository {

    override suspend fun drinksList(startsWith: String): Result<DrinkResponseModel> = runCatching {
        drinksApi.searchDrink(startsWith = startsWith)
    }


}