package com.fitlog.domain.repository

import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay


interface ExerciseReposiotry {

    fun addExercise(newExercise: Exercise, trainingDay: TrainingDay)

    fun deleteExercise(exerciseToDelete: Exercise)

    fun getExercisesByTrainingDay(trainingDay: TrainingDay) : List<Exercise>

}