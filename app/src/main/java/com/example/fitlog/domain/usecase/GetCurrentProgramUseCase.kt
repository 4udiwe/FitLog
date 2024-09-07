package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.example.fitlog.domain.repository.TrainingProgramRepository

class GetCurrentProgramUseCase (private val repository: TrainingProgramRepository){
    fun execute(): TrainingProgramDB {
        return repository.getCurrentProgram()
    }
}