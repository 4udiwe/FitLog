package com.example.fitlog.domain.repository

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB

interface ExerciseReposiotry {

    fun addExercise(newExercise: ExerciseDB, trainingDay: TrainingDayDB)

    fun deleteExercise(exerciseToDelete: ExerciseDB)

    fun getExercisesByTrainingDay(trainingDay: TrainingDayDB) : List<ExerciseDB>

}