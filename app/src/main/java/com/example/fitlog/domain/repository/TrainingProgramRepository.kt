package com.example.fitlog.domain.repository

import com.example.fitlog.data.models.TrainingProgramDB

interface TrainingProgramRepository {

    fun getCurrentProgram() : TrainingProgramDB

    fun setCurrentProgram(newCurrentProgram: TrainingProgramDB)

    fun getAllPrograms() : List<TrainingProgramDB>
}