package com.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fitlog.data.models.ExerciseDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert suspend fun addExercise(exerciseDB: ExerciseDB)
    @Update suspend fun updateExercise(exerciseDB: ExerciseDB)
    @Delete suspend fun deleteExercise(exerciseDB: ExerciseDB)

    @Query ("select * from exercises")
    fun all() : Flow<List<ExerciseDB>>

    @Query ("select * from exercises where training_day_id == :id")
    suspend fun exercisesOfDay(id: Int?) : List<ExerciseDB>

    @Query ("select * from exercises where exercise_id == :id")
    suspend fun findExercise(id : Int) : ExerciseDB
}