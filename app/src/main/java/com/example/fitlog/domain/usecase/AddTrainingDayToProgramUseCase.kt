package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.TrainingDayRepository
import com.example.fitlog.domain.repository.TrainingProgramRepository

class AddTrainingDayToProgramUseCase(
    private val dayRepository: TrainingDayRepository,
    private val programRepository: TrainingProgramRepository
) {

    fun execute(newTrainingDay: TrainingDayDB){
        val currentProgram = programRepository.getCurrentProgram()
        dayRepository.addTrainingDay(newTrainingDay,currentProgram)
    }
}