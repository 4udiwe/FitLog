package com.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.repository.ExerciseReposiotry

class AddExerciseToDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(newExercise: com.example.fitlog.data.models.ExerciseDB, trainingDay: com.example.fitlog.data.models.TrainingDayDB){
        exerciseReposiotry.addExercise(newExercise, trainingDay)
    }
}