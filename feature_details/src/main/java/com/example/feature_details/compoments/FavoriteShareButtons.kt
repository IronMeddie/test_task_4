package com.example.feature_details.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feature_details.R
import com.example.theme.IconButton

@Composable
fun FavoriteShareButtons(
    modifier: Modifier = Modifier,
    onClickShare: () -> Unit,
    onClickFavorite: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(painter = painterResource(id = R.drawable.favorite),
            contentDescription = "favorite button",
            tint = IconButton,
            modifier = Modifier
                .clickable { onClickFavorite() }
                .padding(13.dp))
        Spacer(
            modifier = Modifier
                .width(12.dp)
                .height(1.dp)
                .background(IconButton)
        )
        Icon(painter = painterResource(id = R.drawable.share),
            contentDescription = "share button",
            tint = IconButton,
            modifier = Modifier
                .clickable { onClickShare() }
                .padding(13.dp))
    }
}