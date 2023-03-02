package com.example.test_task_4.bottom_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.navigation.Routes
import com.example.theme.GreyIcon
import com.example.theme.GreyIconBack
import com.example.theme.SelectedIcon
import com.example.theme.dimens
import com.example.test_task_4.R

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
            .height(MaterialTheme.dimens.BottomMenuHeight)
            .background(MaterialTheme.colors.onSurface)
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { i, it ->
            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                .background(if (selected == it.route) GreyIconBack else MaterialTheme.colors.onSurface),
                contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = it.icon),
                    contentDescription = "bottom menu icon $i",
                    tint = if (selected == it.route) SelectedIcon else GreyIcon
                )
            }
        }
    }
}
data class BottomMenuItem(val route: String, val icon: Int)


fun getMenu(): List<BottomMenuItem> {
    val listOf = listOf(
        BottomMenuItem(Routes.Home, R.drawable.menu_home),
        BottomMenuItem(Routes.Favorite, R.drawable.menu_favorite),
        BottomMenuItem(Routes.Cart, R.drawable.menu_cart),
        BottomMenuItem(Routes.Messages, R.drawable.menu_messages),
        BottomMenuItem(Routes.Profile, R.drawable.menu_user),
    )
    return listOf
}