package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class AddTrainingProgramUseCase(
    private val programRepository: TrainingProgramRepository
) {

    suspend fun execute(newProgram: TrainingProgram){
        programRepository.addProgram(newProgram)
    }
}