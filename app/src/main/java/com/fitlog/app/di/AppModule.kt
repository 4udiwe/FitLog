package com.fitlog.app.di

import com.fitlog.app.viewmodel.ProgramViewModel
import com.fitlog.app.viewmodel.SettingViewModel
import com.fitlog.app.viewmodel.TrainingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<ProgramViewModel> {
        ProgramViewModel(
            getCurrentProgramUseCase = get(),
            getTrainingDaysOfProgramUseCase = get(),
            addTrainingDayToProgramUseCase = get(),
            deleteTrainingDayUseCase = get(),
            getExercisesOfDayUseCase = get(),
            addExerciseToDayUseCase = get(),
            deleteExerciseUseCase = get()
        )
    }

    viewModel<TrainingViewModel> {
        TrainingViewModel(
            getTrainingDaysOfProgramUseCase = get(),
            getExercisesOfDayUseCase = get()
        )
    }

    viewModel<SettingViewModel> {
        SettingViewModel(
            getCurrentProgramUseCase = get(),
            setCurrentProgramUseCase = get(),
            getAllProgramsUseCase = get(),
            addTrainingProgramUseCase = get(),
            deleteTrainingProgramUseCase = get()
        )
    }
}