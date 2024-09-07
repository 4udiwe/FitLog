package com.example.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitlog.data.models.TrainingProgramDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramDao {
    @Insert suspend fun addProgram(program: TrainingProgramDB)
    @Update suspend fun updateProgram(program: TrainingProgramDB)
    @Delete suspend fun removeProgram(program: TrainingProgramDB)

    @Query ("select * from training_programs")
    fun all() : Flow<List<ProgramDB>>

    @Query ("select * from training_programs where is_current == 1")
    suspend fun currentProgram() : ProgramDB

    @Query ("select * from training_programs where is_current == 1")
    fun getCurrentProgram() : Flow<ProgramDB>

    @Query ("select * from training_programs where training_program_id == :id")
    suspend fun findProgram(id : Int) : ProgramDB
}