package com.example.fitlog.domain.repository

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.data.models.TrainingProgramDB

interface TrainingDayRepository {

    fun addTrainingDay(newDay: TrainingDayDB, program: TrainingProgramDB)

    fun deleteTrainingDay(dayToDelete: TrainingDayDB)

    fun getTrainingDaysByProgram(program: TrainingProgramDB) : List<TrainingDayDB>
}