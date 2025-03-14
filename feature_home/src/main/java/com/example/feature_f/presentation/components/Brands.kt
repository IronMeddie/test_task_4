package com.example.feature_f.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_f.R
import com.example.feature_f.presentation.Brand
import com.example.theme.AppIconButton
import com.example.theme.CatBack
import com.example.theme.IconButton

@Composable
fun Brands(brands: List<Brand>) {
    Column {
        PartHeader(stringResource(R.string.brands)) { }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 11.dp)) {
            items(brands) {
                BrandItem(it)
            }
        }
    }
}


@Composable
fun BrandItem(item: Brand) {

    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .width(114.dp)
            .height(149.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = item.imageUrl),
            contentDescription = "image latest",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 7.dp, bottom = 7.dp, top = 91.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(12.dp)
                    .background(CatBack), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier.padding(horizontal = 7.dp),
                    style = MaterialTheme.typography.h2,
                    fontSize = 6.sp,
                    fontWeight = FontWeight.W600
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "",
                modifier = Modifier.width(75.dp),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600,
                fontSize = 9.sp,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600,
                fontSize = 7.sp,
                color = MaterialTheme.colors.onSurface
            )

        }

        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(5.dp)
                .clip(
                    CircleShape
                )
                .size(20.dp)
                .background(AppIconButton)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add to cart button latest",
                modifier = Modifier.size(8.dp),
                tint = IconButton
            )
        }
    }
}

