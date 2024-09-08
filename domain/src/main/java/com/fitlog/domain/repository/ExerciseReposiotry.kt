package com.fitlog.domain.repository

import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay


interface ExerciseReposiotry {
    suspend fun addExercise(newExercise: Exercise)
    suspend fun deleteExercise(exerciseToDelete: Exercise)
    suspend fun getExercisesByTrainingDay(trainingDay: TrainingDay) : List<Exercise>
}