package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.repository.TrainingProgramRepository

class GetCurrentProgramUseCase (private val repository: TrainingProgramRepository){
    fun execute(): com.example.fitlog.data.models.TrainingProgramDB {
        return repository.getCurrentProgram()
    }
}