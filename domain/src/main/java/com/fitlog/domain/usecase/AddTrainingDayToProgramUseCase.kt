package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository

class AddTrainingDayToProgramUseCase(
    private val dayRepository: TrainingDayRepository
) {
    suspend fun execute(newTrainingDay: TrainingDay, currentProgram: TrainingProgram){
        dayRepository.addTrainingDay(newTrainingDay.copy(programId = currentProgram.id))
    }
}