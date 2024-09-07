package com.example.fitlog.app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.fitlog.app.ProgramViewModel
import com.example.fitlog.R
import com.example.fitlog.app.navigation.BottomNavigation
import com.example.fitlog.app.navigation.NavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    programViewModel: ProgramViewModel = viewModel(factory = ProgramViewModel.factory)

) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
                 TopAppBar(title = { Text(text = "FitLog") })
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        values ->
        NavGraph(navHostController = navController, values, programViewModel)
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    TextButton(
        onClick = { onClick.invoke() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ){
            Text("Add")
            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add")
        }
    }
}