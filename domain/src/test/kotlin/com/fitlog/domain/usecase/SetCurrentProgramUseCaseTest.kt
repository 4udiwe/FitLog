package com.fitlog.domain.usecase

import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class SetCurrentProgramUseCaseTest {

    private val programRepository = mock<TrainingProgramRepository>()
    @AfterEach
    fun tearDown() {
        Mockito.reset(programRepository)
    }
    @Test
    fun `prev current program was null`() {
        val useCase = SetCurrentProgramUseCase(programRepository)

        val newCurrentProgramParam = TrainingProgram(id = 1, name = "new current", desc = "test desc", current = false)
        val currentProgramParam = null

        runBlocking {
            useCase.execute(newCurrentProgram = newCurrentProgramParam, currentProgram = currentProgramParam)
            Mockito.verify(programRepository, times(1)).addProgram(newCurrentProgramParam.copy(current = true))
        }
    }

    @Test
    fun `prev current program was not null`(){
        val useCase = SetCurrentProgramUseCase(programRepository)

        val newCurrentProgramParam = TrainingProgram(id = 1, name = "new current", desc = "test desc", current = false)
        val currentProgramParam = TrainingProgram(id = 10, name = "old current", desc = "test desc", current = true)

        runBlocking {
            useCase.execute(newCurrentProgram = newCurrentProgramParam, currentProgram = currentProgramParam)
            Mockito.verify(programRepository, times(1)).addProgram(currentProgramParam.copy(current = false))
            Mockito.verify(programRepository, times(1)).addProgram(newCurrentProgramParam.copy(current = true))
        }
    }
}