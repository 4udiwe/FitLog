package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingDayRepository

class DeleteTrainingDayUseCase(private val trainingDayRepository: TrainingDayRepository) {

    fun execute(dayToDelete: TrainingDay){
        trainingDayRepository.deleteTrainingDay(dayToDelete)
    }
}