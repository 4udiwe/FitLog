package com.fitlog.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.usecase.AddTrainingProgramUseCase
import com.fitlog.domain.usecase.DeleteTrainingProgramUseCase
import com.fitlog.domain.usecase.GetAllProgramsUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.SetCurrentProgramUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingViewModel(
    getCurrentProgramUseCase: GetCurrentProgramUseCase,
    getAllProgramsUseCase: GetAllProgramsUseCase,
    private val setCurrentProgramUseCase: SetCurrentProgramUseCase,
    private val addTrainingProgramUseCase: AddTrainingProgramUseCase,
    private val deleteTrainingProgramUseCase: DeleteTrainingProgramUseCase
): ViewModel() {

    var programsListFlow: Flow<List<TrainingProgram>> = getAllProgramsUseCase.execute()
    var currentProgramFlow: Flow<TrainingProgram?> = getCurrentProgramUseCase.execute()

    fun setCurrentProgram(newCurrentProgram: TrainingProgram, currentProgram: TrainingProgram?) = viewModelScope.launch{
        setCurrentProgramUseCase.execute(newCurrentProgram = newCurrentProgram, currentProgram = currentProgram)
    }
    fun addProgram(newProgram: TrainingProgram) = viewModelScope.launch {
        addTrainingProgramUseCase.execute(newProgram = newProgram)
    }
    fun deleteProgram(program: TrainingProgram) = viewModelScope.launch{
        deleteTrainingProgramUseCase.execute(programToDelete = program)
    }
}