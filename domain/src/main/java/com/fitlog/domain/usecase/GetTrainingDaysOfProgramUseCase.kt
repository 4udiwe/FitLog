package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

class GetTrainingDaysOfProgramUseCase (private val dayRepository: TrainingDayRepository){

    fun execute(program: TrainingProgram): Flow<List<TrainingDay>> {
        return dayRepository.getTrainingDaysByProgram(program)
    }
}