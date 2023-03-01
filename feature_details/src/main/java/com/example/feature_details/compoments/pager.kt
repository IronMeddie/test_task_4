package com.example.feature_details.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.theme.AppIconButton
import com.example.theme.dimens
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsImagePager(images: List<String>, state: PagerState) {
Box() {
    HorizontalPager(
        count = images.size,
        modifier = Modifier
            .fillMaxWidth(),
        state = state

    ) { page ->
        Box(
            modifier = Modifier
                .padding(MaterialTheme.dimens.MainPagerItemPadding)
                .height(MaterialTheme.dimens.MainPagerItemHeight)
                .fillMaxWidth()
                .clip(MaterialTheme.dimens.MainPagerIemShape)
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = "product photo",
                contentScale = ContentScale.Crop,

                )
        }
    }
    FavoriteShareButtons(modifier = Modifier
        .padding(MaterialTheme.dimens.ButtonsBoxShareAndFavoritePaddings)
        .width(MaterialTheme.dimens.ButtonsBoxShareAndFavoriteWidth)
        .height(MaterialTheme.dimens.ButtonsBoxShareAndFavoritePHeight)
        .clip(MaterialTheme.dimens.ButtonsBoxShareAndFavoriteShape)
        .background(AppIconButton)
        .align(Alignment.TopEnd)
        , onClickFavorite = {}, onClickShare = {})
}

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SmalImagePager(images: List<String>, state: PagerState, onClick : (Int) -> Unit) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val pading = (screenWidth.value.dp - MaterialTheme.dimens.SmallPagerIemWidth)/2


    HorizontalPager(
        count = images.size,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = pading),
        itemSpacing = MaterialTheme.dimens.SmallPagerIemSpacing,
        state = state

    ) { page ->
        val modifier =
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = ScaleFactor(1f, 1f),
                        stop = ScaleFactor(1.25f, 1.25f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleY
                    }
                    lerp(
                        start = ScaleFactor(0f, 0f),
                        stop = ScaleFactor(4f, 0f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        shadowElevation = scale.scaleX
                        shape = RoundedCornerShape(6.dp)
                    }
                }
                .width(MaterialTheme.dimens.SmallPagerIemWidth)
                .height(MaterialTheme.dimens.SmallPagerIemHeight)
                .clickable { onClick(page) }
                .clip(MaterialTheme.dimens.SmallPagerIemCornerRadius)


        Box(
            modifier = modifier
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = "small pager image",
                contentScale = ContentScale.Crop)

        }
    }
}