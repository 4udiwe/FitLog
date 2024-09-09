package com.fitlog.app.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.fitlog.data.db.TrainingProgramDataBase
import com.fitlog.data.repository.TrainingProgramRepositoryImpl
import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.usecase.AddExerciseToDayUseCase
import com.fitlog.domain.usecase.AddTrainingDayToProgramUseCase
import com.fitlog.domain.usecase.DeleteExerciseUseCase
import com.fitlog.domain.usecase.DeleteTrainingDayUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.GetExercisesOfDayUseCase
import com.fitlog.domain.usecase.GetTrainingDaysOfProgramUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class ProgramViewModel(
    private val getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getTrainingDaysOfProgramUseCase: GetTrainingDaysOfProgramUseCase,
    private val addTrainingDayToProgramUseCase: AddTrainingDayToProgramUseCase,
    private val deleteTrainingDayUseCase: DeleteTrainingDayUseCase,
    private val getExercisesOfDayUseCase: GetExercisesOfDayUseCase,
    private val addExerciseToDayUseCase: AddExerciseToDayUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {


    val currentProgramFlow = getCurrentProgramUseCase.execute()

    var selectedDay: TrainingDay? = null
    var selectedExercise: Exercise? = null


    fun getDays(program: TrainingProgram): Flow<List<TrainingDay>> {
        return getTrainingDaysOfProgramUseCase.execute(program)
    }
    fun addDay(newDay: TrainingDay, program: TrainingProgram) = viewModelScope.launch {
            addTrainingDayToProgramUseCase.execute(newDay, program)
    }
    fun deleteDay(oldDay: TrainingDay) = viewModelScope.launch {
        deleteTrainingDayUseCase.execute(oldDay)
    }
    fun getExercises(day: TrainingDay): Flow<List<Exercise>> {
        return getExercisesOfDayUseCase.execute(day)
    }
    fun addExercise(newExer: Exercise, day: TrainingDay) = viewModelScope.launch {
        addExerciseToDayUseCase.execute(newExer, day)
    }
    fun deleteExercise(oldExer: Exercise) = viewModelScope.launch {
        deleteExerciseUseCase.execute(oldExer)

    }
}