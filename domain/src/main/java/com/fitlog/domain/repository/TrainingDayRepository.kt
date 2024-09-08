package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram


interface TrainingDayRepository {
    suspend fun addTrainingDay(newDay: TrainingDay)
    suspend fun deleteTrainingDay(dayToDelete: TrainingDay)
    suspend fun getTrainingDaysByProgram(program: TrainingProgram) : List<TrainingDay>
}