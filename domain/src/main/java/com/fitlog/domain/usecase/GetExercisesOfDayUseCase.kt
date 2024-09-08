package com.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.repository.ExerciseReposiotry

class GetExercisesOfDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(trainingDay: com.example.fitlog.data.models.TrainingDayDB): List<com.example.fitlog.data.models.ExerciseDB> {
        return exerciseReposiotry.getExercisesByTrainingDay(trainingDay)
    }
}