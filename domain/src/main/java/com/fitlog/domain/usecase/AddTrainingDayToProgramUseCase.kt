package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository

class AddTrainingDayToProgramUseCase(
    private val dayRepository: TrainingDayRepository,
    private val programRepository: TrainingProgramRepository
) {

    fun execute(newTrainingDay: com.example.fitlog.data.models.TrainingDayDB){
        val currentProgram = programRepository.getCurrentProgram()
        dayRepository.addTrainingDay(newTrainingDay,currentProgram)
    }
}