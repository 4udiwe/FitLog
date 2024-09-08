package com.fitlog.domain.usecase

import com.example.fitlog.data.models.ExerciseDB
import com.fitlog.domain.repository.ExerciseReposiotry

class DeleteExerciseUseCase (private val exerciseReposiotry: ExerciseReposiotry){

    fun execute(exerciseToDelete: com.example.fitlog.data.models.ExerciseDB){
        exerciseReposiotry.deleteExercise(exerciseToDelete)
    }
}