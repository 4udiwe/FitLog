package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class AddTrainingProgramUseCaseTest {
    private val programRepository = mock<TrainingProgramRepository>()
    @Test
    fun `should execute method`() {
        val useCase = AddTrainingProgramUseCase(programRepository)

        val testProgramParam = TrainingProgram(id = 1, name = "test name", desc = "test desc")

        runBlocking {
            useCase.execute(testProgramParam)
            Mockito.verify(programRepository, times(1)).addProgram(testProgramParam)
        }
    }
}