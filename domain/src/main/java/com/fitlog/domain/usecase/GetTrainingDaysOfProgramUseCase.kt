package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository

class GetTrainingDaysOfProgramUseCase (private val programRepository: TrainingProgramRepository, private val dayRepository: TrainingDayRepository){

    fun execute(): List<TrainingDay> {
        return dayRepository.getTrainingDaysByProgram(programRepository.getCurrentProgram())
    }
}