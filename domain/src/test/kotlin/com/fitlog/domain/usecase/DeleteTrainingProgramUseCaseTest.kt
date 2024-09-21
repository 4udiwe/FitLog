package com.fitlog.domain.usecase


import com.fitlog.domain.models.TrainingProgram
import com.fitlog.domain.repository.TrainingProgramRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class DeleteTrainingProgramUseCaseTest {

    private val trainingProgramRepository = mock<TrainingProgramRepository>()
    @Test
    fun execute() {
        val useCase = DeleteTrainingProgramUseCase(trainingProgramRepository)
        val testProgramParam = TrainingProgram(id = 1, name = "test name", desc = "test desc")
        runBlocking {
            useCase.execute(testProgramParam)
            Mockito.verify(trainingProgramRepository, times(1)).deleteProgram(testProgramParam)
        }
    }
}