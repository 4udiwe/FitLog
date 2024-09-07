package com.example.fitlog.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitlog.data.models.TrainingDayDB
import kotlinx.coroutines.flow.Flow


@Dao
interface DayDao {
    @Insert
    suspend fun addDay(trainingDayDB: TrainingDayDB)
    @Update
    suspend fun updateDat(trainingDayDB: TrainingDayDB)
    @Delete
    suspend fun removeDay(trainingDayDB: TrainingDayDB)

    @Query("select * from training_days")
    fun all() : Flow<List<TrainingDayDB>>

    @Query("select * from training_days where training_program_id == :id")
    fun daysOfProgram(id: Int?) : Flow<List<TrainingDayDB>>

    @Query("select * from training_days where training_day_id== :id")
    suspend fun findDay(id : Int) : TrainingDayDB
}