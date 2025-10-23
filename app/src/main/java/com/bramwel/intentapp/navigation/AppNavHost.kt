package com.bramwel.intentapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bramwel.intentapp.ui.theme.screens.home.HomeScreen
import com.bramwel.learn.ui.theme.Screens.splashscreen.SplashScreen


@Composable
fun AppNavHost(modifier: Modifier=Modifier,navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_SPLASH) {

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){
        composable(ROUTE_SPLASH){
            SplashScreen(navController)
        }

        composable(ROUTE_HOME) {
            HomeScreen(navController = navController)
        }

    }


}