package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.example.fitlog.domain.repository.TrainingProgramRepository

class GetAllProgramsUseCase (private val repository: TrainingProgramRepository){

    fun execute() : List<TrainingProgramDB> {
        return repository.getAllPrograms()
    }
}