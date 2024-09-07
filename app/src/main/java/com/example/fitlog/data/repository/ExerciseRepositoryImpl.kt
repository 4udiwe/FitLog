package com.example.fitlog.data.repository

import com.example.fitlog.data.db.TrainingProgramDataBase
import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.ExerciseReposiotry

class ExerciseRepositoryImpl : ExerciseReposiotry{
    override fun addExercise(newExercise: ExerciseDB, trainingDay: TrainingDayDB) {
        val db = TrainingProgramDataBase

    }

    override fun deleteExercise(exerciseToDelete: ExerciseDB) {
        TODO("Not yet implemented")
    }

    override fun getExercisesByTrainingDay(trainingDay: TrainingDayDB): List<ExerciseDB> {
        TODO("Not yet implemented")
    }

}