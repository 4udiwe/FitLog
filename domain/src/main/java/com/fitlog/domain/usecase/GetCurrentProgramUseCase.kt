package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentProgramUseCase (private val repository: TrainingProgramRepository){
    fun execute(): Flow<TrainingProgram> {
        return repository.getCurrentProgram()
    }
}