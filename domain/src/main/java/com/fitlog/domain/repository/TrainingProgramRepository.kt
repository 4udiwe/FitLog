package com.fitlog.domain.repository

import com.example.fitlog.data.models.TrainingProgramDB

interface TrainingProgramRepository {

    fun getCurrentProgram() : com.example.fitlog.data.models.TrainingProgramDB

    fun setCurrentProgram(newCurrentProgram: com.example.fitlog.data.models.TrainingProgramDB)

    fun getAllPrograms() : List<com.example.fitlog.data.models.TrainingProgramDB>
}