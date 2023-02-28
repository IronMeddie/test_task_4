package com.example.feature_details

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.theme.ImageBorder
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsImagePager(images: List<String>, state: PagerState) {

    HorizontalPager(
        count = images.size,
        modifier = Modifier
            .fillMaxWidth(),
        state = state

    ) { page ->
        Box(
            modifier = Modifier

                .padding(end = 52.dp)
                .height(279.dp)
                .width(328.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(9.dp))
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = "product photo",
                contentScale = ContentScale.Crop,

            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SmalImagePager(images: List<String>, state: PagerState, onClick : (Int) -> Unit) {


    HorizontalPager(
        count = images.size,
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 170.dp),
        itemSpacing = 10.dp, state = state

    ) { page ->
        val modifier =
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = ScaleFactor(1f,1f),
                        stop = ScaleFactor(1.25f,1.25f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1.25f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleY
                    }
                }
                .width(66.dp)
                .height(38.dp)
                .clickable { onClick(page) }
                .shadow(4.dp, RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp))

        Box(
            modifier = modifier
        ) {
            AsyncImage(
                model = images[page],
                contentDescription = null,
                contentScale = ContentScale.Crop)

        }
    }
}