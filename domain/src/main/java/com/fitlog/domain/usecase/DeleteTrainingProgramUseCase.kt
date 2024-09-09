package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class DeleteTrainingProgramUseCase(
    private val programRepository: TrainingProgramRepository
) {

    suspend fun execute(programToDelete: TrainingProgram){
        programRepository.deleteProgram(program = programToDelete)
    }
}