package com.example.feature_profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.database.entity.User
import com.example.navigation.navigateToLoginScreen
import com.example.theme.Border

@Composable
fun ProfileScreen(navController: NavController,viewModel: ProfileViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(it)){
            item { ProfileTopBar() }
            item { Button(onClick = {
                viewModel.logOut()
                navController.navigateToLoginScreen()
            }) {
                Text(text = "Log out")
            } }
            item {  }
            item {  }
            item {  }
            item {  }
            item {  }
        }
    }
}

@Composable
fun ProfileTopBar(){
    Box(modifier = Modifier
        .height(60.dp)
        .fillMaxWidth()) {
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "icon back")
        }
        Text(text = stringResource(R.string.profile), modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun UserInfo(user: User){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(model = if (user.avatar.isNotEmpty()) user.avatar else R.drawable.avatar, contentDescription = "user avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(61.dp)
                .border(1.dp, Border, CircleShape)
        )
        Text(text = stringResource(R.string.change_photo))
        Spacer(modifier = Modifier.height(17.dp))
        Text(text = stringResource(R.string.name))
    }
}