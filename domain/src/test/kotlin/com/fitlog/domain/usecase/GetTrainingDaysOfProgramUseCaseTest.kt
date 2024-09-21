package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetTrainingDaysOfProgramUseCaseTest {

    private val dayRepository = mock<TrainingDayRepository>()
    @Test
    fun execute() {
        val useCase = GetTrainingDaysOfProgramUseCase(dayRepository)

        val testProgramParam = TrainingProgram(id = 1, name = "test program name", desc = "test desc")
        val expected = flowOf(
            listOf(
                TrainingDay(id = 1, name = "test day1"),
                TrainingDay(id = 2, name = "test day2"),
                TrainingDay(id = 3, name = "test day3")
            )
        )
        var actual: Flow<List<TrainingDay>>
        Mockito.`when`(dayRepository.getTrainingDaysByProgram(testProgramParam)).thenReturn(expected)

        runBlocking {
            actual = useCase.execute(testProgramParam)
            Assertions.assertEquals(expected, actual, "wrong return")
            Mockito.verify(dayRepository, times(1)).getTrainingDaysByProgram(testProgramParam)
        }
    }
}