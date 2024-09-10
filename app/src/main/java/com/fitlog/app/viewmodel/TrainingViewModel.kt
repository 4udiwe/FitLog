package com.fitlog.app.viewmodel

import androidx.lifecycle.ViewModel
import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.GetExercisesOfDayUseCase
import com.fitlog.domain.usecase.GetTrainingDaysOfProgramUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class TrainingViewModel(
    private val getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getTrainingDaysOfProgramUseCase: GetTrainingDaysOfProgramUseCase,
    private val getExercisesOfDayUseCase: GetExercisesOfDayUseCase
): ViewModel(){

    val currentProgramFlow = getCurrentProgramUseCase.execute()

    fun getDays(program: TrainingProgram?): Flow<List<TrainingDay>> {
        return if (program != null)
            getTrainingDaysOfProgramUseCase.execute(program)
        else
            emptyFlow()
    }

    fun getExercises(day: TrainingDay): Flow<List<Exercise>> {
        return getExercisesOfDayUseCase.execute(day)
    }
}