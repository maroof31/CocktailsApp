package com.example.cocktails.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Typography.title1: TextStyle
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = FontColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

    }

val Typography.subtitle1: TextStyle
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = FontColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }



val Typography.body1: TextStyle
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = FontColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }

val Typography.body1secondary: TextStyle
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = SecondaryFontColor,
            fontSize = 14.sp,
        )
    }

val Typography.body2: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = FontColor,
            fontSize = 12.sp,
        )
    }

val Typography.body2secondary: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = SecondaryFontColor,
            fontSize = 12.sp,
        )
    }


