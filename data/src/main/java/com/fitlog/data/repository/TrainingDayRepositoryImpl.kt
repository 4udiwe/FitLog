package com.fitlog.data.repository


import com.fitlog.data.db.DayDao
import com.fitlog.data.db.ExerciseDao
import com.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrainingDayRepositoryImpl(
    private val dayDao: DayDao,
    private val exerciseDao: ExerciseDao
) : TrainingDayRepository {
    override suspend fun addTrainingDay(newDay: TrainingDay) = dayDao.addDay(TrainingDayDB(
        newDay.id,
        newDay.name,
        newDay.programId
    ))

    override suspend fun deleteTrainingDay(dayToDelete: TrainingDay) {
        val exercises = exerciseDao.exercisesOfDay(dayToDelete.id)
        exercises.forEach {
            exerciseDao.deleteExercise(it)
        }
        dayDao.deleteDay(
            TrainingDayDB(
                dayToDelete.id,
                dayToDelete.name,
                dayToDelete.programId
            )
        )
    }

    override fun getTrainingDaysByProgram(program: TrainingProgram): Flow<List<TrainingDay>> =
        dayDao.daysOfProgramFlow(program.id).map { list->
            list.map {
                it.toDomainTrainingDayModel()
            }
        }
}