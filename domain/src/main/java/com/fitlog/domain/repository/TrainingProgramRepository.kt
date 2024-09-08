package com.fitlog.domain.repository

import com.fitlog.domain.models.TrainingProgram

interface TrainingProgramRepository {

    fun getCurrentProgram() : TrainingProgram

    fun setCurrentProgram(newCurrentProgram: TrainingProgram)

    fun getAllPrograms() : List<TrainingProgram>
}