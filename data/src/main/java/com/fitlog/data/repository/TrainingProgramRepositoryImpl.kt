package com.fitlog.data.repository


import com.fitlog.data.db.ProgramDao
import com.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

class TrainingProgramRepositoryImpl(
    private val programDao: ProgramDao
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


    override suspend fun deleteProgram(program: TrainingProgram) =
        programDao.deleteProgram(
            TrainingProgramDB(
                id = program.id,
                name = program.name,
                desc = program.desc,
                current = program.current)
        )

    override fun getAllPrograms(): Flow<List<TrainingProgram>> =
        programDao.all().map { list ->
            list.map { it.toDomainTrainingProgramModel() }
            }
}