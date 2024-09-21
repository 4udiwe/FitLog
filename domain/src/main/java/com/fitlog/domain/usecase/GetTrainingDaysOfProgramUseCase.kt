package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository
import kotlinx.coroutines.flow.Flow

class GetTrainingDaysOfProgramUseCase (private val dayRepository: TrainingDayRepository){

    fun execute(program: TrainingProgram): Flow<List<TrainingDay>> {
        return dayRepository.getTrainingDaysByProgram(program)
    }
}