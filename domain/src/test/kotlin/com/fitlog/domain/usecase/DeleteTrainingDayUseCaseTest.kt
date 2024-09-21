package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.TrainingDayRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class DeleteTrainingDayUseCaseTest {

    private val trainingDayRepository = mock<TrainingDayRepository>()

    @Test
    fun execute() {
        val testDayParam = TrainingDay(id = 1, name = "test name")

        val useCase = DeleteTrainingDayUseCase(trainingDayRepository)

        runBlocking {
            useCase.execute(testDayParam)
            Mockito.verify(trainingDayRepository, times(1)).deleteTrainingDay(testDayParam)
        }

    }
}