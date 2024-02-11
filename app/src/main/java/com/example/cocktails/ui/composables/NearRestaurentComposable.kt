package com.example.cocktails.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cocktails.R
import com.example.cocktails.ui.theme.body2
import com.example.cocktails.ui.theme.body2secondary
import com.example.cocktails.ui.theme.subtitle1

@Composable
fun NearRestaurant(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(color = colorResource(id = R.color.off_white))
        .padding(15.dp),

    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.restaurant),
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 6.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Blue Restaurant",
                 style = MaterialTheme.typography.subtitle1,
                modifier = Modifier

            )
            Text(text = "10:00 AM - 3:30 PM",
                style = MaterialTheme.typography.body2secondary,
                modifier = Modifier

            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Steve st Rood",
                    color = Color.Red,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "4.5",
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier,
                    )
                    Image(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(16.dp),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                    )
                }


            }
        }
    }
}