package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class AddExerciseToDayUseCaseTest {

    private val exerciseReposiotry = mock<ExerciseReposiotry>()
    @Test
    fun `should execute method`() {
        val useCase = AddExerciseToDayUseCase(exerciseReposiotry)

        val testExerciseParam = Exercise(id = 1, name = "test exer name")
        val testDayParam = TrainingDay(id = 1, name = "test day name")
        runBlocking {
            useCase.execute(testExerciseParam, testDayParam)
            Mockito.verify(exerciseReposiotry, Mockito.times(1)).addExercise(testExerciseParam.copy(dayId = testDayParam.id))
        }
    }
}