package com.fitlog.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fitlog.app.viewmodel.ProgramViewModel
import com.fitlog.app.screens.ProgramScreen
import com.fitlog.app.screens.SettingsScreen
import com.fitlog.app.screens.TrainingScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    programViewModel: ProgramViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = "screen_1",
    ){
        composable("screen_1"){
            TrainingScreen(paddingValues = paddingValues, programViewModel = programViewModel)
        }
        composable("screen_2"){
            ProgramScreen(paddingValues = paddingValues, programViewModel = programViewModel)
        }
        composable("screen_3"){
            SettingsScreen(paddingValues = paddingValues, programViewModel = programViewModel)
        }
    }

}