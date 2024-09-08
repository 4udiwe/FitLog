package com.fitlog.domain.usecase


import com.fitlog.domain.models.Exercise
import com.fitlog.domain.repository.ExerciseReposiotry

class DeleteExerciseUseCase (private val exerciseReposiotry: ExerciseReposiotry){

    fun execute(exerciseToDelete: Exercise){
        exerciseReposiotry.deleteExercise(exerciseToDelete)
    }
}