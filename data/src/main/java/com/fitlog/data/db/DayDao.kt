package com.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fitlog.data.models.TrainingDayDB
import kotlinx.coroutines.flow.Flow


@Dao
interface DayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDay(trainingDayDB: TrainingDayDB)
    @Delete
    suspend fun deleteDay(trainingDayDB: TrainingDayDB)

    @Query("select * from training_days")
    fun all() : Flow<List<TrainingDayDB>>

    @Query("select * from training_days where training_program_id == :id")
    suspend fun daysOfProgram(id: Int?) : List<TrainingDayDB>
    @Query("select * from training_days where training_program_id == :id")
    fun daysOfProgramFlow(id: Int?) : Flow<List<TrainingDayDB>>

}