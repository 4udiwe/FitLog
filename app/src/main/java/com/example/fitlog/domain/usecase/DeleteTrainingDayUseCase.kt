package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.TrainingDayRepository

class DeleteTrainingDayUseCase(private val trainingDayRepository: TrainingDayRepository) {

    fun execute(dayToDelete: TrainingDayDB){
        trainingDayRepository.deleteTrainingDay(dayToDelete)
    }
}