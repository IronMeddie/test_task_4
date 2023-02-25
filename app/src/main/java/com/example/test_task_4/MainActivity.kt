package com.example.test_task_4

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.feature_f.presentation.HomeScreen
import com.example.feature_sign_in.screens.sign_in.SignInScreen
import com.example.feature_sign_in.screens.welcome_back.SignUpScreen
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
                // A surface container using the 'background' color from the theme
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
                    NavHost(
                        navController = navController,
                        startDestination = "sign_in_route",
                        modifier = Modifier.padding(it)
                    ) {
                        composable("home_route") {
                            HomeScreen()
                        }
                        composable("sign_in_route") {
                            SignInScreen(navController = navController)
                        }
                        composable("sign_up_route") {
                            SignUpScreen(navController = navController)
                        }
                    }
                }
            }
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
            .padding(vertical = 13.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { i, it ->
            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { navController.navigate(it.route) }
                .background(if (selected == it.route) GreyIconBack else MaterialTheme.colors.background), contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = it.icon), contentDescription = "bottom menu icon $i", tint = if (selected == it.route) SelectedIcon else GreyIcon  )
            }
        }
    }
}


data class BottomMenuItem(val route: String, val icon: Int)


fun getMenu() = listOf(
    BottomMenuItem("home_route",R.drawable.menu_home),
    BottomMenuItem("favorite_route",R.drawable.menu_favorite),
    BottomMenuItem("cart_route",R.drawable.menu_cart),
    BottomMenuItem("messages_route",R.drawable.menu_messages),
    BottomMenuItem("user_route",R.drawable.menu_user),
)







