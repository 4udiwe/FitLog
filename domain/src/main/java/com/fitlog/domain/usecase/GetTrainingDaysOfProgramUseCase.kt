package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository

class GetTrainingDaysOfProgramUseCase (private val programRepository: TrainingProgramRepository, private val dayRepository: TrainingDayRepository){

    suspend fun execute(): List<TrainingDay>? {
        return programRepository.getCurrentProgram()
            ?.let { dayRepository.getTrainingDaysByProgram(it) }
    }
}