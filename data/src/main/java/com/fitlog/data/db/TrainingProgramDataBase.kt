package com.fitlog.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fitlog.data.models.ExerciseDB
import com.fitlog.data.models.TrainingDayDB
import com.fitlog.data.models.TrainingProgramDB


@Database(
    version = 1,
    entities = [
        ExerciseDB::class,
        TrainingDayDB::class,
        TrainingProgramDB::class
    ],
)
abstract class TrainingProgramDataBase : RoomDatabase() {
    abstract fun exercises() : ExerciseDao
    abstract fun days() : DayDao
    abstract fun programs() : ProgramDao

    companion object {
        fun createDB(context: Context) : TrainingProgramDataBase {
            return Room.databaseBuilder(
                context,
                TrainingProgramDataBase::class.java,
                "programs.db"
            )
                .createFromAsset("database/training_programs.db")
                .build()
        }
    }
}