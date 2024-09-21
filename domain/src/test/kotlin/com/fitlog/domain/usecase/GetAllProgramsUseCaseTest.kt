package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetAllProgramsUseCaseTest {

    private val programRepository = mock<TrainingProgramRepository>()
    @Test
    fun execute() {
        val useCase = GetAllProgramsUseCase(programRepository)
        val expected = flowOf(
            listOf(
                TrainingProgram(id = 1, name = "test name1", desc = "test desc1"),
                TrainingProgram(id = 2, name = "test name2", desc = "test desc2"),
                TrainingProgram(id = 3, name = "test name3", desc = "test desc3")
            )
        )
        Mockito.`when`(programRepository.getAllPrograms()).thenReturn(expected)
        var actual: Any
        runBlocking {
            actual = useCase.execute()
            Assertions.assertEquals(expected, actual, "Wrong rerun")
            Mockito.verify(programRepository, times(1)).getAllPrograms()
        }
    }
}