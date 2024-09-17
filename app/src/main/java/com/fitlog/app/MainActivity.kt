package com.fitlog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fitlog.app.screens.MainScreen
import com.fitlog.app.viewmodel.ProgramViewModel
import com.fitlog.app.viewmodel.SettingViewModel
import com.fitlog.app.viewmodel.TrainingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val programVM by viewModel<ProgramViewModel>()
    private val trainingVM by viewModel<TrainingViewModel>()
    private val settingVM by viewModel<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MainScreen(
                trainingViewModel = trainingVM,
                programViewModel = programVM,
                settingViewModel = settingVM
            )
        }
    }
}


