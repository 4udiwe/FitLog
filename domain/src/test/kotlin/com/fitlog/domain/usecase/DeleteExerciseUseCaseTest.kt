package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.repository.ExerciseReposiotry
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class DeleteExerciseUseCaseTest {

    private val exerciseReposiotry = mock<ExerciseReposiotry>()
    @Test
    fun `should execute method`() {
        val testExerciseParam = Exercise(id = 1, name = "test name")
        val useCase = DeleteExerciseUseCase(exerciseReposiotry)

        runBlocking {
            useCase.execute(testExerciseParam)
            Mockito.verify(exerciseReposiotry, times(1)).deleteExercise(testExerciseParam)
        }

    }
}