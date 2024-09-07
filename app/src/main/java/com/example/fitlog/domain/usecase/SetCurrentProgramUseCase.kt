package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.example.fitlog.domain.repository.TrainingProgramRepository

class SetCurrentProgramUseCase(private val repository: TrainingProgramRepository) {

    fun execute(newCurrentProgram: TrainingProgramDB){
        repository.setCurrentProgram(newCurrentProgram)
    }
}