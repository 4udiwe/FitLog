package com.fitlog.app.viewmodel

import androidx.lifecycle.ViewModel
import com.fitlog.domain.usecase.GetExercisesOfDayUseCase
import com.fitlog.domain.usecase.GetTrainingDaysOfProgramUseCase

class TrainingViewModel(
    private val getTrainingDaysOfProgramUseCase: GetTrainingDaysOfProgramUseCase,
    private val getExercisesOfDayUseCase: GetExercisesOfDayUseCase
): ViewModel(){
}