package com.example.test_task_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.feature_f.presentation.HomeScreen
import com.example.feature_profile.ProfileScreen
import com.example.feature_sign_in.screens.sign_in.SignInScreen
import com.example.feature_sign_in.screens.welcome_back.SignUpScreen
import com.example.navigation.OTHER
import com.example.navigation.Routes
import com.example.navigation.navigateToLoginScreen
import com.example.theme.GreyIcon
import com.example.theme.GreyIconBack
import com.example.theme.SelectedIcon
import com.example.theme.Test_task_4Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test_task_4Theme {
                MyNavHost()
            }
        }
    }
}

@Composable
fun MyNavHost(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination?.route in getMenu().map { it.route })
                BottomNavMenu(navController = navController)
        }
    ) {
//        LaunchedEffect(key1 = true) {
////        viewModel.user.collectLatest {
////            if (user is DataResource.Success && user.data == null) {
////                navController.navigateToLoginScreen()
////            }
////        }
//            navController.navigateToLoginScreen()
//
//
//        }

        NavHost(
            navController = navController,
            startDestination = Routes.Home,
            modifier = Modifier.padding(it)
        ) {
            composable(Routes.Home) {
                HomeScreen(navController)
            }
            composable(Routes.SignIn) {
                SignInScreen(navController = navController)
            }
            composable(Routes.LogIn) {
                SignUpScreen(navController = navController)
            }
            composable(Routes.Profile) {
                ProfileScreen(navController)
            }
            OTHER()
        }
    }
}


@Composable
fun BottomNavMenu(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selected = currentDestination?.route
    val items = getMenu()
    Row(
        Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(63.dp)
            .background(MaterialTheme.colors.onSurface)
            .padding(vertical = 13.dp)
        , horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { i, it ->
            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(it.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                .background(if (selected == it.route) GreyIconBack else MaterialTheme.colors.onSurface), contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = it.icon), contentDescription = "bottom menu icon $i", tint = if (selected == it.route) SelectedIcon else GreyIcon  )
            }
        }
    }
}


data class BottomMenuItem(val route: String, val icon: Int)


fun getMenu() = listOf(
    BottomMenuItem(Routes.Home,R.drawable.menu_home),
    BottomMenuItem(Routes.Favorite,R.drawable.menu_favorite),
    BottomMenuItem(Routes.Cart,R.drawable.menu_cart),
    BottomMenuItem(Routes.Messages,R.drawable.menu_messages),
    BottomMenuItem(Routes.Profile,R.drawable.menu_user),
)




