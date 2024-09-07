package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.TrainingDayRepository
import com.example.fitlog.domain.repository.TrainingProgramRepository

class GetTrainingDaysOfProgramUseCase (private val programRepository: TrainingProgramRepository, private val dayRepository: TrainingDayRepository){

    fun execute(): List<TrainingDayDB> {
        return dayRepository.getTrainingDaysByProgram(programRepository.getCurrentProgram())
    }
}