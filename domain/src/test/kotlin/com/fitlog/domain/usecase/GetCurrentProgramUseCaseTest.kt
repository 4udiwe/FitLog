package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetCurrentProgramUseCaseTest {

    val programRepository = mock<TrainingProgramRepository>()
    @Test
    fun execute() {
        val useCase = GetCurrentProgramUseCase(programRepository)

        val expected = flowOf(TrainingProgram(id = 1, name = "test name", desc = "test desc"))
        val actual: Flow<TrainingProgram?>

        Mockito.`when`(programRepository.getCurrentProgram()).thenReturn(expected)

        runBlocking {
            actual = useCase.execute()
            Assertions.assertEquals(expected, actual, "wrong return")
            Mockito.verify(programRepository, times(1)).getCurrentProgram()
        }
    }
}