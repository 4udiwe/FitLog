package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository

class GetTrainingDaysOfProgramUseCase (private val programRepository: TrainingProgramRepository, private val dayRepository: TrainingDayRepository){

    fun execute(): List<com.example.fitlog.data.models.TrainingDayDB> {
        return dayRepository.getTrainingDaysByProgram(programRepository.getCurrentProgram())
    }
}