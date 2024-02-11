package com.example.cocktails.data.datasource.remote

import com.example.cocktails.data.model.DrinkResponseModel
import com.example.cocktails.data.model.DrinksItemDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksApi {

    @GET("/api/json/v1/1/search.php/")
    suspend fun searchDrink(
        @Query("s") startsWith: String?,
    ): DrinkResponseModel

}