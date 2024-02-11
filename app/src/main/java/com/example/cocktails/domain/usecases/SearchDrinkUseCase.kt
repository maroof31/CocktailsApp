package com.example.cocktails.domain.usecases

import com.example.cocktails.data.model.DrinkItem
import com.example.cocktails.data.model.DrinksItemDTO
import com.example.cocktails.data.repository.DrinksRepository
import com.example.cocktails.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDrinkUseCase @Inject constructor(
    private val repo: DrinksRepository
) {
    suspend fun execute(startsWith: String): Flow<DataState<List<DrinkItem>>> = flow{
        emit(DataState.Loading)
        repo.drinksList(startsWith)
            .onSuccess {
                emit(DataState.Success(mapDrinksItemDtoToDrinkItem(it.drinks)))
            }.onFailure {
                emit(DataState.Error(it))
            }
    }
}
private fun mapDrinksItemDtoToDrinkItem(drinksItemDTOList: List<DrinksItemDTO?>?):List<DrinkItem> {
    val list = mutableListOf<DrinkItem>()
    drinksItemDTOList?.forEach { drinksItemDTO->
        val ingredientsList = mutableListOf<String>()
        for (i in 1..15) {
            val fieldName = "strIngredient$i"
            val field = DrinksItemDTO::class.java.getDeclaredField(fieldName)
            field.isAccessible = true
            val ingredient = field.get(drinksItemDTO) as? String

            if (!ingredient.isNullOrBlank()) {
                ingredientsList.add(ingredient)
            }
        }

       list.add(
        DrinkItem(
            imageUrl = drinksItemDTO?.strDrinkThumb,
            drinkName = drinksItemDTO?.strDrink.orEmpty(),
            category = drinksItemDTO?.strCategory.orEmpty(),
            isAlcoholic = drinksItemDTO?.strAlcoholic.orEmpty(),
            ingredients = ingredientsList.joinToString(", "),
            measure = (1..15).mapNotNull {
                val fieldName = "strMeasure$it"
                val field = DrinksItemDTO::class.java.getDeclaredField(fieldName)
                field.isAccessible = true
                val measure = field.get(drinksItemDTO) as? String

                measure.takeIf { !it.isNullOrBlank() }
            },
            instructions = drinksItemDTO?.strInstructions.orEmpty()
        )
       )
    }
   return list
}

