package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry

class GetExercisesOfDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(trainingDay: TrainingDay): List<Exercise> {
        return exerciseReposiotry.getExercisesByTrainingDay(trainingDay)
    }
}