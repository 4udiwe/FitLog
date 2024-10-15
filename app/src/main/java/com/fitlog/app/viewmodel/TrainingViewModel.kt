package com.fitlog.app.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getTrainingDaysOfProgramUseCase: GetTrainingDaysOfProgramUseCase,
    private val getExercisesOfDayUseCase: GetExercisesOfDayUseCase
): ViewModel(){

    val isTrainingLive = MutableLiveData(false)
    val currentTrainingDayLive = MutableLiveData(TrainingDay(name = ""))
    val currentExerciseIndexLive = MutableLiveData(0)


    val currentProgramFlow = getCurrentProgramUseCase.execute()

    fun getDays(program: TrainingProgram?): Flow<List<TrainingDay>> {
        return if (program != null)
            getTrainingDaysOfProgramUseCase.execute(program)
        else
            emptyFlow()
    }
    fun getExercises(): Flow<List<Exercise>> {
        return getExercisesOfDayUseCase.execute(currentTrainingDayLive.value!!)
    }

}