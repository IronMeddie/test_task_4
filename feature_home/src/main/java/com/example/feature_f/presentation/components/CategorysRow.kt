package com.example.feature_f.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_f.R
import com.example.theme.CategoryText
import com.example.theme.GreyIconBack
import com.example.theme.textStyles

@Composable
fun CategoryRow() {
    val categorys = getCategorys()
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(categorys) {
            CategoryItem(it)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(horizontal = 8.dp)
        .width(52.dp)
        .clickable { }) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(GreyIconBack), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = "category icon"
            )
        }
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = category.title,
            style = MaterialTheme.textStyles.CategoryName,
        )
    }
}

fun getCategorys(): List<Category> = listOf(
    Category(R.drawable.c_phones, "Phones"),
    Category(R.drawable.c_headphones, "Headphones"),
    Category(R.drawable.c_games, "Games"),
    Category(R.drawable.c_cars, "Cars"),
    Category(R.drawable.c_furniture, "Furniture"),
    Category(R.drawable.c_kids, "Kids"),
    Category(R.drawable.c_phones, "Gods"),
    Category(R.drawable.c_phones, "RockGroups"),

    )


data class Category(val icon: Int, val title: String)