package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.repository.TrainingProgramRepository

class GetAllProgramsUseCase (private val repository: TrainingProgramRepository){

    fun execute() : List<com.example.fitlog.data.models.TrainingProgramDB> {
        return repository.getAllPrograms()
    }
}