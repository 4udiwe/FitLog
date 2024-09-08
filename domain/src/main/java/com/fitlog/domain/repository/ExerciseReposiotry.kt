package com.fitlog.domain.repository

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB

interface ExerciseReposiotry {

    fun addExercise(newExercise: com.example.fitlog.data.models.ExerciseDB, trainingDay: com.example.fitlog.data.models.TrainingDayDB)

    fun deleteExercise(exerciseToDelete: com.example.fitlog.data.models.ExerciseDB)

    fun getExercisesByTrainingDay(trainingDay: com.example.fitlog.data.models.TrainingDayDB) : List<com.example.fitlog.data.models.ExerciseDB>

}