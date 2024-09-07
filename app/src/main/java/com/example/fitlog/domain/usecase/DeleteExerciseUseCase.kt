package com.example.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.domain.repository.ExerciseReposiotry

class DeleteExerciseUseCase (private val exerciseReposiotry: ExerciseReposiotry){

    fun execute(exerciseToDelete: ExerciseDB){
        exerciseReposiotry.deleteExercise(exerciseToDelete)
    }
}