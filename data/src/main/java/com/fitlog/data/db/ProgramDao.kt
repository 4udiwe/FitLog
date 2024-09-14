package com.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fitlog.data.models.TrainingProgramDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProgram(program: TrainingProgramDB)
    @Delete
    suspend fun deleteProgram(program: TrainingProgramDB)

    @Query ("select * from training_programs")
    fun all() : Flow<List<TrainingProgramDB>>

    @Query ("SELECT * FROM training_programs WHERE is_current = 1 LIMIT 1")
    fun getCurrentProgram() : Flow<TrainingProgramDB>

}