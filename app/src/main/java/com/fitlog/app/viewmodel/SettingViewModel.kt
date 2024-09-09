package com.fitlog.app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.usecase.AddTrainingProgramUseCase
import com.fitlog.domain.usecase.DeleteTrainingProgramUseCase
import com.fitlog.domain.usecase.GetAllProgramsUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.SetCurrentProgramUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SettingViewModel(
    private val getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getAllProgramsUseCase: GetAllProgramsUseCase,
    private val setCurrentProgramUseCase: SetCurrentProgramUseCase,
    private val addTrainingProgramUseCase: AddTrainingProgramUseCase,
    private val deleteTrainingProgramUseCase: DeleteTrainingProgramUseCase
): ViewModel() {

    private val currentProgramLiveMutable = MutableLiveData<TrainingProgram>()
    val currentProgram: LiveData<TrainingProgram> = currentProgramLiveMutable

    private val allProgramsListLiveMutable = MutableLiveData<List<TrainingProgram>>()
    val allProgramsList: LiveData<List<TrainingProgram>> = allProgramsListLiveMutable
    init {
        getCurrentProgram()
        setAllProgramsList()
    }

    fun getCurrentProgram() = viewModelScope.launch {
        currentProgramLiveMutable.value = getCurrentProgramUseCase.execute()
        Log.d("RRR", "Get current program ${currentProgramLiveMutable.value}")
    }
    fun setAllProgramsList() = viewModelScope.launch {
        Log.d("RRR", "Get all programs")
        allProgramsListLiveMutable.value = getAllProgramsUseCase.execute()
    }
    fun setCurrentProgram(newCurrentProgram: TrainingProgram) = viewModelScope.launch{

        val job = viewModelScope.launch {
            Log.d("RRR", "set current program: $newCurrentProgram")
            setCurrentProgramUseCase.execute(newCurrentProgram = newCurrentProgram)
        }
        job.join()
        val job1 = getCurrentProgram()
        job1.join()

    }

    fun addProgram(newProgram: TrainingProgram) = viewModelScope.launch {
        Log.d("RRR", "add program")

        addTrainingProgramUseCase.execute(newProgram = newProgram)
    }
    fun deleteProgram(program: TrainingProgram) = viewModelScope.launch {
        Log.d("RRR", "delete program")

        deleteTrainingProgramUseCase.execute(programToDelete = program)
    }
}