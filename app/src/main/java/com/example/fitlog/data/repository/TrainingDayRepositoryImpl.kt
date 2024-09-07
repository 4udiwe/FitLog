package com.example.fitlog.data.repository

import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.data.models.TrainingProgramDB
import com.example.fitlog.domain.repository.TrainingDayRepository

class TrainingDayRepositoryImpl : TrainingDayRepository {
    override fun addTrainingDay(newDay: TrainingDayDB, program: TrainingProgramDB) {
        TODO("Not yet implemented")
    }

    override fun deleteTrainingDay(dayToDelete: TrainingDayDB) {
        TODO("Not yet implemented")
    }

    override fun getTrainingDaysByProgram(program: TrainingProgramDB): List<TrainingDayDB> {
        TODO("Not yet implemented")
    }

}