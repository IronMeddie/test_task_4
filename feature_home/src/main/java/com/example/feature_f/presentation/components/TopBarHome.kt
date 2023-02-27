package com.example.feature_f.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.database.entity.User
import com.example.feature_f.R
import com.example.theme.GreyBorder
import com.example.theme.GreyTextLoc

@Composable
fun TopBarHome(user: User?) {
    Column() {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
            }) {
                Icon(
                    painter = painterResource(R.drawable.gamburger),
                    contentDescription = "gamburger"
                )
            }
            Row() {
                Text("Trade By ", style = MaterialTheme.typography.h3)
                Text(
                    "Data",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary
                )
            }
            IconButton(onClick = {

            }) {
                val painter = rememberAsyncImagePainter(model =  if (user != null && user.avatar.isNotEmpty()) user.avatar else R.drawable.avatar  )
                Image(
                    painter,
                    contentDescription = "avatar",
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, GreyBorder, CircleShape)
                        .size(31.dp)
                )
            }
        }
        Row(modifier = Modifier
            .padding(end = 16.dp)
            .clickable { }
            .align(Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {
            Text(
                text = stringResource(R.string.location),
                fontWeight = FontWeight.W400,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontFamily = FontFamily(
                    Font(com.example.theme.R.font.poppins)
                ),
                color = GreyTextLoc
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "location arrow"
            )
        }
    }

}