package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingDayRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class AddTrainingDayToProgramUseCaseTest {

    private val dayRepository = mock<TrainingDayRepository>()
    @Test
    fun `should execute method`() {
        val useCase = AddTrainingDayToProgramUseCase(dayRepository)

        val testDayParam = TrainingDay(id = 1, name = "test day name")
        val testProgramParam = TrainingProgram(id = 1, name = "test program name", desc = "test desc")
        runBlocking {
            useCase.execute(testDayParam, testProgramParam)
            Mockito.verify(dayRepository, Mockito.times(1)).addTrainingDay(testDayParam.copy(programId = testProgramParam.id))
        }
    }
}