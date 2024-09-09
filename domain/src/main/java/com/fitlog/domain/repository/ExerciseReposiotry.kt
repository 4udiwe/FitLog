package com.fitlog.domain.repository

import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import kotlinx.coroutines.flow.Flow


interface ExerciseReposiotry {
    suspend fun addExercise(newExercise: Exercise)
    suspend fun deleteExercise(exerciseToDelete: Exercise)
    fun getExercisesByTrainingDay(trainingDay: TrainingDay) : Flow<List<Exercise>>
}