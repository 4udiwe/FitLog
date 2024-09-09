package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingProgram

interface TrainingProgramRepository {

    suspend fun getCurrentProgram() : TrainingProgram?
    suspend fun setProgram(program: TrainingProgram)
    suspend fun deleteProgram(program: TrainingProgram)
    suspend fun getAllPrograms() : List<TrainingProgram>
}