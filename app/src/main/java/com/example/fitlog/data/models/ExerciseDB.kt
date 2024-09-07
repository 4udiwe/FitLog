package com.example.fitlog.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Exercises",
    foreignKeys = [
        ForeignKey(
            entity = TrainingDayDB::class,
            parentColumns = ["training_day_id"],
            childColumns = ["training_day_id"]
        )
    ]
)
data class ExerciseDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    var id: Int? = null,
    @ColumnInfo(name = "exercise_name")
    var name: String,
    @ColumnInfo(name = "exercise_sets")
    var sets: Int = 4,
    @ColumnInfo(name = "exercise_reps")
    var reps: Int = 12,
    @ColumnInfo(name = "exercise_rest_time")
    var restTime: Int = 300,
    @ColumnInfo(name = "training_day_id")
    var dayId: Int? = null
)