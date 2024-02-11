package com.example.cocktails.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DrinkItem(
    val imageUrl: String? = null,
    val drinkName: String = "",
    val category: String ="",
    val isAlcoholic: String = "",
    val ingredients: String = "",
    val measure: List<String> = emptyList(),
    val instructions: String =""
): Parcelable