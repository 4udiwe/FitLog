package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingProgram
import kotlinx.coroutines.flow.Flow

interface TrainingProgramRepository {

    fun getCurrentProgram() : Flow<TrainingProgram>
    suspend fun addProgram(program: TrainingProgram)
    suspend fun deleteProgram(program: TrainingProgram)
    fun getAllPrograms() : Flow<List<TrainingProgram>>
}