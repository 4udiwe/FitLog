package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.domain.repository.ExerciseReposiotry

class GetExercisesOfDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(trainingDay: TrainingDayDB): List<ExerciseDB> {
        return exerciseReposiotry.getExercisesByTrainingDay(trainingDay)
    }
}