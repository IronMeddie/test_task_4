package com.example.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class TextStyles(
    private val font1 : FontFamily = FontFamily(Font(R.font.montserat)),
    private val font2 : FontFamily = FontFamily(Font(R.font.poppins)),


   val CategoryName: TextStyle = TextStyle(
       fontFamily = font2,
       fontWeight = FontWeight.W500,
       fontSize = 8.sp,
       letterSpacing = -(0.3).sp,
       color = CategoryText
   ),




    val AddToCartButtonText : TextStyle = TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W600,
        fontSize = 8.sp,
        letterSpacing = -(0.3).sp,
        color = White
    ),
    val AddToCartQuantity : TextStyle = TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W500,
        fontSize = 9.sp,
        letterSpacing = -(0.3).sp,
        color = OnotherOneGrey
    ),
    val DetailsTitle : TextStyle = TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp,
        letterSpacing = -(0.3).sp,
        color = com.example.theme.DetailsTitle
    ),
    val DetailsDescription : TextStyle = TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W400,
        fontSize = 9.sp,
        letterSpacing = -(0.3).sp,
        color = OnotherOneGrey
    ),
    val DetailsRating : TextStyle = TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W600,
        fontSize = 9.sp,
        letterSpacing = -(0.3).sp,
        color = com.example.theme.DetailsTitle
    ),
    val DetailsReviews : TextStyle =  TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W400,
        fontSize = 9.sp,
        letterSpacing = -(0.3).sp,
        color = OnotherOneGrey
    ),

    val DetailsColor : TextStyle =  TextStyle(
        fontFamily = font2,
        fontWeight = FontWeight.W600,
        fontSize = 10.sp,
        letterSpacing = -(0.3).sp,
        color = com.example.theme.DetailsColor
    ),
)


val LocalTextStyles = compositionLocalOf { TextStyles() }


val MaterialTheme.textStyles: TextStyles
    @Composable
    @ReadOnlyComposable
    get() = LocalTextStyles.current