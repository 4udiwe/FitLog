package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.ExerciseReposiotry

class AddExerciseToDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(newExercise: ExerciseDB, trainingDay: TrainingDayDB){
        exerciseReposiotry.addExercise(newExercise, trainingDay)
    }
}