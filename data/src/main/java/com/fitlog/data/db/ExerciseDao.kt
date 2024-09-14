package com.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fitlog.data.models.ExerciseDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercise(exerciseDB: ExerciseDB)
    @Delete suspend fun deleteExercise(exerciseDB: ExerciseDB)

    @Query ("select * from exercises")
    fun all() : Flow<List<ExerciseDB>>
    @Query ("select * from exercises where training_day_id == :id")
    suspend fun exercisesOfDay(id: Int?) : List<ExerciseDB>
    @Query ("select * from exercises where training_day_id == :id")
    fun exercisesOfDayFlow(id: Int?) : Flow<List<ExerciseDB>>

    @Query ("select * from exercises where exercise_id == :id")
    suspend fun findExercise(id : Int) : ExerciseDB
}