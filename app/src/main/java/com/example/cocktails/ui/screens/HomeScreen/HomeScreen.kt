package com.example.cocktails.ui.screens.HomeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cocktails.R
import com.example.cocktails.data.model.DrinkItem
import com.example.cocktails.domain.uiEvent.HomeScreenUiEvent
import com.example.cocktails.ui.composables.CircularProgressBar
import com.example.cocktails.ui.composables.NearRestaurant
import com.example.cocktails.ui.composables.NothingFound
import com.example.cocktails.ui.theme.body1
import com.example.cocktails.ui.theme.body1secondary
import com.example.cocktails.ui.theme.body2
import com.example.cocktails.ui.theme.subtitle1
import com.example.cocktails.ui.theme.title1
import com.example.cocktails.utils.orFalse

@Composable
fun HomeScreen(onclickDrink: (item:DrinkItem)-> Unit) {
    val viewModel = hiltViewModel<MainViewModel>()
    val drinkItemList = viewModel.drinksList.value
    val isLoading = viewModel.isLoading.value
    val isError = viewModel.isError.value

   Column(modifier = Modifier
       .fillMaxSize()
       .background(Color.White)
       .padding(horizontal = 16.dp),
       horizontalAlignment = Alignment.Start) {

       Text(
           text = stringResource(id = R.string.lets_eat_quality_food),
           color = Color.Black,
           style = MaterialTheme.typography.title1,
           modifier = Modifier
               .padding(top=80.dp)
       )

       SearchField(
           onSearchQueryType = { viewModel.uiEvent(HomeScreenUiEvent.SearchQueryTyped(it)) }
       )

       if (isLoading) {
           CircularProgressBar(true)
       }

       NearRestaurantHeader()
       if(drinkItemList?.isEmpty().orFalse()){
           NothingFound()
       }else{
           DrinksList(
               items = drinkItemList?: emptyList(),
               onclick = { onclickDrink(it) }
           )
       }
       if(isError){
         Toast.makeText(
             LocalContext.current,
             R.string.oops_error,
             Toast.LENGTH_SHORT
         ).show()
       }

   }

}

@Composable
fun NearRestaurantHeader(){
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = stringResource(id = R.string.near_restaurant),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
            )
            Text(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
            )
        }
        NearRestaurant()
    }
}

@Composable
fun SearchField(
    onSearchQueryType: (value: String)-> Unit
){
    var value by remember {
        mutableStateOf("")
    }

    BasicTextField(
        modifier = Modifier
            .padding(top = 20.dp),
        value = value,
        onValueChange = { newText ->
            value = newText
            onSearchQueryType(value)
        },
        textStyle = MaterialTheme.typography.subtitle1,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(end = 64.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.off_white),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Favorite icon",
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                if (value.isEmpty()) {
                    Text(
                        stringResource(id = R.string.search),
                        style = MaterialTheme.typography.body1secondary
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun DrinksList(items: List<DrinkItem>, onclick: (item:DrinkItem)-> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
            .background(color = colorResource(id = R.color.off_white)),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items) { item ->
            CocktailItemComposable(
                imageUrl = item.imageUrl.toString(),
                cocktailName = item.drinkName,
                category = item.category,
                onclick ={ onclick(item) }
            )
        }
    }
}



@Composable
fun CocktailItemComposable(
    imageUrl:String,
    cocktailName: String,
    category: String,
    onclick: () -> Unit
){
    Column(modifier = Modifier
        .clickable { onclick() }
        .clip(shape = RoundedCornerShape(12.dp))
        .width(100.dp)
        .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        horizontalAlignment = Alignment.Start,

    ){
        AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholderdrink),
            modifier = Modifier
                .height(120.dp)
                .clip(shape = RoundedCornerShape(12.dp))
        )

        Text(text = cocktailName,
            color = Color.Black,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(top=4.dp)
        )

        Text(text = category,
            color = Color.Black,
            style = MaterialTheme.typography.body1secondary,
            modifier = Modifier
                .padding(top=2.dp)

        )

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()) {
            Spacer(modifier = Modifier)
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .border(.5.dp, Color.Red, RoundedCornerShape(5.dp)),

                ){
                Text(text = stringResource(id = R.string.view),
                    color = Color.Red,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}