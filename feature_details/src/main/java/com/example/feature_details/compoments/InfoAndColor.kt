package com.example.feature_details.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.example.feature_details.R
import com.example.models.Details
import com.example.theme.ColorBorder
import com.example.theme.dimens
import com.example.theme.textStyles

@Composable
fun InfoAndColor(details: Details) {

    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = details.name, style = MaterialTheme.textStyles.DetailsTitle)
            Text(
                text = "$ " + details.price,
                style = MaterialTheme.textStyles.DetailsTitle,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = details.description, style = MaterialTheme.textStyles.DetailsDescription)
        Spacer(modifier = Modifier.height(14.dp))
        Row() {
            AsyncImage(
                model = R.drawable.star,
                contentDescription = "star",
                modifier = Modifier.size(MaterialTheme.dimens.DetailsStarSize)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = details.rating.toString(), style = MaterialTheme.textStyles.DetailsRating)
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "(${details.number_of_reviews} ${stringResource(R.string.reviews)})",
                style = MaterialTheme.textStyles.DetailsReviews
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = stringResource(R.string.color), style = MaterialTheme.textStyles.DetailsColor)
        Spacer(modifier = Modifier.height(11.dp))
        if (details.colors.isNotEmpty()) {
            Row() {
                var currentColor by remember { mutableStateOf(details.colors[0]) }
                details.colors.forEach {
                    val modifier = if (currentColor == it) {
                        Modifier
                            .width(34.dp)
                            .height(26.dp)
                            .clip(
                                RoundedCornerShape(9.dp)
                            )
                            .background(Color(it.toColorInt()))
                            .border(2.dp, ColorBorder, RoundedCornerShape(9.dp))

                    } else {
                        Modifier
                            .width(34.dp)
                            .height(26.dp)
                            .clip(
                                RoundedCornerShape(9.dp)
                            )
                            .background(Color(it.toColorInt()))
                            .clickable { currentColor = it }
                    }
                    Box(modifier = modifier)
                    Spacer(modifier = Modifier.width(14.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}