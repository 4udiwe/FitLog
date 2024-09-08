package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.repository.TrainingDayRepository

class DeleteTrainingDayUseCase(private val trainingDayRepository: TrainingDayRepository) {

    fun execute(dayToDelete: com.example.fitlog.data.models.TrainingDayDB){
        trainingDayRepository.deleteTrainingDay(dayToDelete)
    }
}