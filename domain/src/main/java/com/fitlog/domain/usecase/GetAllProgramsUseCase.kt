package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class GetAllProgramsUseCase (private val programRepository: TrainingProgramRepository){

    suspend fun execute() : List<TrainingProgram> {
        return programRepository.getAllPrograms()
    }
}