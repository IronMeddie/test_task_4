package com.example.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateToLoginScreen(nanOptions: NavOptions? = null){
    this.navigate(Routes.SignIn, nanOptions)
}

fun NavController.navigateToSignUp(navOptions: NavOptions? = null){
    this.navigate(Routes.LogIn)
}

fun NavController.navigateToMainScreen(navOptions: NavOptions? = NavOptions.Builder().setPopUpTo(this.graph.id, true, true)
    .setLaunchSingleTop(true).build()){
    this.navigate(Routes.Home)
}

fun  NavGraphBuilder.OTHER(){
    composable(Routes.Messages){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Messages Screen")
        }
    }
    composable(Routes.Cart){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Cart Screen")
        }
    }
    composable(Routes.Favorite){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Favorite Screen")
        }
    }
}
