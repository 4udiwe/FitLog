package com.fitlog.data.repository


import com.fitlog.data.db.DayDao
import com.fitlog.data.db.ExerciseDao
import com.fitlog.data.db.ProgramDao
import com.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrainingProgramRepositoryImpl(
    private val programDao: ProgramDao,
    private val dayDao: DayDao,
    private val exerciseDao: ExerciseDao
) : TrainingProgramRepository {


    override fun getCurrentProgram(): Flow<TrainingProgram?> =
        programDao.getCurrentProgram().map {
            it?.toDomainTrainingProgramModel()
        }

    override suspend fun addProgram(program: TrainingProgram) =
        programDao.addProgram(
            TrainingProgramDB(
                id = program.id,
                name = program.name,
                desc = program.desc,
                current = program.current)
        )


    override suspend fun deleteProgram(program: TrainingProgram) {
        val days = dayDao.daysOfProgram(program.id)
        days.forEach { day ->
            exerciseDao.exercisesOfDay(day.id).forEach { exercise ->
                exerciseDao.deleteExercise(exercise)
            }
            dayDao.deleteDay(day)
        }
        programDao.deleteProgram(
            TrainingProgramDB(
                id = program.id,
                name = program.name,
                desc = program.desc,
                current = program.current
            )
        )
    }

    override fun getAllPrograms(): Flow<List<TrainingProgram>> =
        programDao.all().map { list ->
            list.map { it.toDomainTrainingProgramModel() }
            }
}