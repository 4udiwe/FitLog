package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow

class GetAllProgramsUseCase (private val programRepository: TrainingProgramRepository){

    fun execute() : Flow<List<TrainingProgram>> {
        return programRepository.getAllPrograms()
    }
}