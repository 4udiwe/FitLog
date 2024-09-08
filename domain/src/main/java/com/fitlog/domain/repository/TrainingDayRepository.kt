package com.fitlog.domain.repository

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.data.models.TrainingProgramDB

interface TrainingDayRepository {

    fun addTrainingDay(newDay: com.example.fitlog.data.models.TrainingDayDB, program: com.example.fitlog.data.models.TrainingProgramDB)

    fun deleteTrainingDay(dayToDelete: com.example.fitlog.data.models.TrainingDayDB)

    fun getTrainingDaysByProgram(program: com.example.fitlog.data.models.TrainingProgramDB) : List<com.example.fitlog.data.models.TrainingDayDB>
}