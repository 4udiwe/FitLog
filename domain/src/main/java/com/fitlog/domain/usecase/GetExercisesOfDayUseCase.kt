package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry
import kotlinx.coroutines.flow.Flow

class GetExercisesOfDayUseCase(private val exerciseReposiotry: ExerciseReposiotry) {

    fun execute(trainingDay: TrainingDay): Flow<List<Exercise>> {
        return exerciseReposiotry.getExercisesByTrainingDay(trainingDay)
    }
}