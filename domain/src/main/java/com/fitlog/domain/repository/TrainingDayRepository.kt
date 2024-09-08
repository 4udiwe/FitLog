package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram


interface TrainingDayRepository {

    fun addTrainingDay(newDay: TrainingDay, program: TrainingProgram)

    fun deleteTrainingDay(dayToDelete: TrainingDay)

    fun getTrainingDaysByProgram(program: TrainingProgram) : List<TrainingDay>
}