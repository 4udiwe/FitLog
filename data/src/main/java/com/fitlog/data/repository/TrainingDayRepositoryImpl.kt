package com.fitlog.data.repository


import com.fitlog.data.db.DayDao
import com.fitlog.data.models.TrainingDayDB
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository

class TrainingDayRepositoryImpl(
    private val dayDao: DayDao
) : TrainingDayRepository {
    override suspend fun addTrainingDay(newDay: TrainingDay) = dayDao.addDay(TrainingDayDB(
        newDay.id,
        newDay.name,
        newDay.programId
    ))

    override suspend fun deleteTrainingDay(dayToDelete: TrainingDay) = dayDao.deleteDay(TrainingDayDB(
        dayToDelete.id,
        dayToDelete.name,
        dayToDelete.programId
    ))

    override suspend fun getTrainingDaysByProgram(program: TrainingProgram): List<TrainingDay> = dayDao.daysOfProgram(program.id).map { it.toDomainTrainingDayModel() }

}