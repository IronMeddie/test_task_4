package com.example.feature_f.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.feature_f.R
import com.example.theme.GreyBorder



@Composable
fun HomeScreen() {

    Column {
        TopBarHome()
    }
}

@Composable
fun TopBarHome(){
    Row(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            //todo
        }){
            Icon(painter = painterResource(R.drawable.gamburger), contentDescription = "gamburger")
        }
        Row() {
            Text("Trade By ")
            Text("Data")
        }
        IconButton(onClick = {
        //todo
         }){
            Image(painterResource(R.drawable.gamburger), contentDescription = "avatar", modifier = Modifier
                .clip(
                    CircleShape
                )
                .border(1.dp, GreyBorder, CircleShape)
                .size(31.dp))
        }
    }
}