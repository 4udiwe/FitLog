package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class GetAllProgramsUseCase (private val repository: TrainingProgramRepository){

    suspend fun execute() : List<TrainingProgram> {
        return repository.getAllPrograms()
    }
}