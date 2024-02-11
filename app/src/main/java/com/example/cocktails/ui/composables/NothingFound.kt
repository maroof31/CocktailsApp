package com.example.cocktails.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktails.R

@Composable
fun NothingFound(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.off_white))

    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription ="",
            contentScale = ContentScale.FillBounds
        )
    }
}