package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class GetCurrentProgramUseCase (private val repository: TrainingProgramRepository){
    suspend fun execute(): TrainingProgram {
        return repository.getCurrentProgram()
    }
}