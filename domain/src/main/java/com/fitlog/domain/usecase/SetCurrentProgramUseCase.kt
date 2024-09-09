package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class SetCurrentProgramUseCase(private val programRepository: TrainingProgramRepository) {

    suspend fun execute(newCurrentProgram: TrainingProgram){

        val currentProgram = programRepository.getCurrentProgram()
        if (currentProgram != null) {
            programRepository.addProgram(currentProgram.copy(current = false))
            programRepository.addProgram(newCurrentProgram.copy(current = true))
        }
    }
}