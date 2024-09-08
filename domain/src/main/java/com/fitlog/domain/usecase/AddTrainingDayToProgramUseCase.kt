package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository

class AddTrainingDayToProgramUseCase(
    private val dayRepository: TrainingDayRepository,
    private val programRepository: TrainingProgramRepository
) {

    suspend fun execute(newTrainingDay: TrainingDay){
        val currentProgram = programRepository.getCurrentProgram()

        dayRepository.addTrainingDay(newTrainingDay.copy(programId = currentProgram.id))
    }
}