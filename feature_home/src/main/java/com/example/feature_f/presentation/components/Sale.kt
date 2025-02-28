package com.example.feature_f.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_f.R
import com.example.models.FlashSale
import com.example.theme.*

@Composable
fun Sale(sale: List<FlashSale>, onClick: (FlashSale) -> Unit) {
    Column {
        PartHeader(stringResource(R.string.flash_sale)) { }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(start = 11.dp)) {
            items(sale) {
                SaleItem(it) { onClick(it) }
            }
        }
    }
}

@Composable
fun SaleItem(item: FlashSale, onClick: () -> Unit) {
    Box(modifier = Modifier
        .padding(end = 9.dp)
        .height(MaterialTheme.dimens.SaleItemHeight)
        .width(MaterialTheme.dimens.SaleItemWidth)
        .clip(RoundedCornerShape(11.dp))
        .clickable { onClick() }

    ) {
        AsyncImage(
            model = item.image_url,
            contentDescription = "image latest",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 9.dp, top = 121.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(12.dp)
                    .background(CatBack), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.category,
                    modifier = Modifier.padding(horizontal = 9.dp),
                    style = MaterialTheme.typography.h2,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.W600
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = item.name,
                modifier = Modifier.width(75.dp),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600,
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "$ " + item.price,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600,
                fontSize = 10.sp,
                color = MaterialTheme.colors.onSurface
            )

        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 4.dp, bottom = 7.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(MaterialTheme.dimens.SaleFavoriteButtonSize)
                    .background(AppIconButton)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "add to favorite",
                    modifier = Modifier.size(12.dp),
                    tint = IconButton
                )
            }

            Spacer(modifier = Modifier.width(5.dp))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(MaterialTheme.dimens.SaleAddButtonSize)
                    .background(AppIconButton)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add to cart button latest",
                    modifier = Modifier.size(13.dp),
                    tint = IconButton
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(7.dp)
                .clip(CircleShape)
                .size(25.dp), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = R.drawable.sale_avatar,
                contentDescription = "saleAvatar",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 7.dp, end = 8.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(Discont)
                .width(46.dp)
                .height(18.dp)
                .align(Alignment.TopEnd), contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.discount.toString() + "% off",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600,
                color = Color.White,
                fontSize = 10.sp
            )
        }

    }
}