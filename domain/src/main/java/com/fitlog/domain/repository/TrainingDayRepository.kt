package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import kotlinx.coroutines.flow.Flow


interface TrainingDayRepository {
    suspend fun addTrainingDay(newDay: TrainingDay)
    suspend fun deleteTrainingDay(dayToDelete: TrainingDay)
    fun getTrainingDaysByProgram(program: TrainingProgram) : Flow<List<TrainingDay>>
}