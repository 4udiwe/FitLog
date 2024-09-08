package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingProgramRepository

class GetAllProgramsUseCase (private val repository: TrainingProgramRepository){

    fun execute() : List<TrainingDay> {
        return repository.getAllPrograms()
    }
}