package com.example.cocktails.ui.screens.DrinkDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cocktails.R
import com.example.cocktails.data.model.DrinkItem
import com.example.cocktails.ui.theme.body1
import com.example.cocktails.utils.orEmpty


@Composable
fun DrinkDetailScreen(
    drink: DrinkItem,
    backClicked: ()-> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box() {
            AsyncImage(
                model = drink.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    ),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(id = R.drawable.placeholderdrink),
                contentDescription = null,
            )

            Image(
                painter = painterResource(
                    id = R.drawable.ic_back
                ),
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp)
                    .clickable { backClicked() }
            )

        }

        Text(
            text = drink.drinkName.orEmpty(),
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 12.dp)

        )

        Text(
            text = stringResource(id = R.string.instructions),
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 12.dp)

        )

        Text(
            text = drink.instructions.orEmpty(),
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 12.dp, start = 16.dp,end= 16.dp)

        )

        Column(modifier = Modifier
            .padding(horizontal = 16.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.ingredients),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
                Text(
                    text = drink.ingredients.orEmpty(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 4.dp,top = 12.dp)

                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.type),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
                Text(
                    text = drink.isAlcoholic.orEmpty(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 4.dp,top = 12.dp)

                )
            }

            Text(
                text = stringResource(id = R.string.size),
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()

            )
            SizeList(drink.measure)
        }


    }


}

@Composable
fun SizeList(items: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),

        ) {
        items(items) { item ->
            Measure(size = item)
        }
    }
}


@Composable
fun Measure(size: String) {
    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .border(3.dp, color = Color.Red, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Text(
            text = size,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.body1
                .copy(fontSize = 24.sp),
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 4.dp)
        )
    }
}

