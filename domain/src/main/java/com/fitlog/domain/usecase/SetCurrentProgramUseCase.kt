package com.fitlog.domain.usecase

import com.example.fitlog.data.models.TrainingProgramDB
import com.fitlog.domain.repository.TrainingProgramRepository

class SetCurrentProgramUseCase(private val repository: TrainingProgramRepository) {

    fun execute(newCurrentProgram: com.example.fitlog.data.models.TrainingProgramDB){
        repository.setCurrentProgram(newCurrentProgram)
    }
}