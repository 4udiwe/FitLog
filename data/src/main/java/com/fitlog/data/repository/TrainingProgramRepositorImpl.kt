package com.fitlog.data.repository


import com.fitlog.data.db.ProgramDao
import com.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository

class TrainingProgramRepositorImpl(
    private val programDao: ProgramDao
) : TrainingProgramRepository {

    override suspend fun getCurrentProgram(): TrainingProgram =
        programDao.getCurrentProgram().toDomainTrainingProgramModel()


    override suspend fun setProgram(program: TrainingProgram) =
        programDao.addProgram(
            TrainingProgramDB(
                name = program.name,
                desc = program.desc,
                id = program.id,
                current = program.current)
        )


    override suspend fun deleteProgram(program: TrainingProgram) =
        programDao.deleteProgram(
            TrainingProgramDB(
                name = program.name,
                desc = program.desc,
                id = program.id,
                current = program.current)
        )

    override suspend fun getAllPrograms(): List<TrainingProgram> = programDao.all().map { it.toDomainTrainingProgramModel() }
}