package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry

class AddExerciseToDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(newExercise: Exercise, trainingDay: TrainingDay){
        exerciseReposiotry.addExercise(newExercise, trainingDay)
    }
}