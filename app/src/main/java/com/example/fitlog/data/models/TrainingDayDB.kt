package com.example.fitlog.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    tableName = "Training_days",
    foreignKeys = [
        ForeignKey(
            entity = TrainingProgramDB::class,
            parentColumns = ["training_program_id"],
            childColumns = ["training_program_id"]
        )
    ]
)
data class TrainingDayDB (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "training_day_id")
    var id: Int? = null,
    @ColumnInfo(name = "training_day_name")
    var name: String,
    @ColumnInfo(name = "training_program_id")
    var programId: Int? = null
)