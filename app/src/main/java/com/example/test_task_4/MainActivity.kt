package com.example.test_task_4

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.navigation
import com.example.domain.use_cases.AuthState
import com.example.domain.use_cases.IsAuth
import com.example.feature_details.DetailsScreen
import com.example.feature_f.presentation.HomeScreen
import com.example.feature_profile.ProfileScreen
import com.example.feature_sign_in.screens.sign_in.SignInScreen
import com.example.feature_sign_in.screens.welcome_back.SignUpScreen
import com.example.navigation.OTHER
import com.example.navigation.Routes
import com.example.navigation.navigateToLoginScreen
import com.example.test_task_4.bottom_menu.BottomNavMenu
import com.example.test_task_4.bottom_menu.getMenu
import com.example.theme.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


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
fun MyNavHost(viewModel: CheckAuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    LaunchedEffect(true) {
        viewModel.getAuth().collect { state->
            when(state){
                is AuthState.NotAuthorizated ->{
                    navController.navigateToLoginScreen()
                }
                else -> Log.d("checkCodeNavHost", "state is: $state")
            }
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

            if (currentDestination?.route in getMenu().map { it.route } || currentDestination?.route == Routes.Details)
                BottomNavMenu(navController = navController)
        }
    ) { paddings->


        NavHost(
            navController = navController,
            startDestination = Routes.MainGraph, modifier = Modifier.padding(if (currentDestination?.route != Routes.Details)paddings else PaddingValues(0.dp))  // в большинстве случаев экран заканчивается там, где начинается боттом меню, но в детайлс это уберем, чтоб сделать эффект когда боттом меню накладывается сверху. Так можно для всех экранов, но тогда в каждом нужно будет прописать spacer снизу чтоб меню не загораживало контент
        ) {
            navigation(startDestination = Routes.SignIn, route = Routes.LoginGraph) {
                composable(Routes.SignIn) {
                    SignInScreen(navController = navController)
                }
                composable(Routes.LogIn) {
                    SignUpScreen(navController = navController)
                }
            }
            navigation(startDestination = Routes.Home, route = Routes.MainGraph) {
                composable(Routes.Home) {
                    HomeScreen(navController)
                }
                composable(Routes.Profile) {
                    ProfileScreen(navController)
                }
                composable(Routes.Details) {
                    DetailsScreen(navController)
                }
                OTHER()
            }
        }
    }


}



@HiltViewModel
class CheckAuthViewModel @Inject constructor(private val isAuth: IsAuth) :
    ViewModel() {
    fun getAuth() = isAuth()
}






