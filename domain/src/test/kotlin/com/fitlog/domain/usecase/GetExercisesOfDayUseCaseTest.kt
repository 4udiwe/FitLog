package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.repository.ExerciseReposiotry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetExercisesOfDayUseCaseTest {

    private val exerciseReposiotry = mock<ExerciseReposiotry>()
    @Test
    fun execute() {
        val testTrainingDayParam = TrainingDay(id = 1, name = "test name")
        val expected = flowOf(
            listOf(
                Exercise(id = 1, name = "test name 1"),
                Exercise(id = 2, name = "test name 2"),
                Exercise(id = 3, name = "test name 3"),
                Exercise(id = 4, name = "test name 4")
            )
        )
        val actual: Flow<List<Exercise>>

        Mockito.`when`(exerciseReposiotry.getExercisesByTrainingDay(testTrainingDayParam)).thenReturn(expected)
        val useCase = GetExercisesOfDayUseCase(exerciseReposiotry)

        runBlocking {
            actual = useCase.execute(testTrainingDayParam)
            Assertions.assertEquals(expected, actual, "wrong return")
            Mockito.verify(exerciseReposiotry, times(1)).getExercisesByTrainingDay(testTrainingDayParam)
        }
    }
}