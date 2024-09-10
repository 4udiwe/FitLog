package com.fitlog.data.repository


import com.fitlog.data.db.ExerciseDao
import com.fitlog.data.models.ExerciseDB
import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExerciseRepositoryImpl(
    private val exerciseDao: ExerciseDao
) : ExerciseReposiotry {

    override suspend fun addExercise(newExercise: Exercise) = exerciseDao.addExercise(
        ExerciseDB(
            id = newExercise.id,
            name = newExercise.name,
            sets = newExercise.sets,
            reps = newExercise.reps,
            restTime = newExercise.restTime,
            dayId = newExercise.dayId
        )
    )

    override suspend fun deleteExercise(exerciseToDelete: Exercise) = exerciseDao.deleteExercise(
        ExerciseDB(
            id = exerciseToDelete.id,
            name = exerciseToDelete.name,
            sets = exerciseToDelete.sets,
            reps = exerciseToDelete.reps,
            restTime = exerciseToDelete.restTime,
            dayId = exerciseToDelete.dayId
        )
    )

    override fun getExercisesByTrainingDay(trainingDay: TrainingDay): Flow<List<Exercise>> =
        exerciseDao.exercisesOfDayFlow(trainingDay.id).map { list ->
            list.map {
                it.toDomainExerciseModel()
            }
        }
}