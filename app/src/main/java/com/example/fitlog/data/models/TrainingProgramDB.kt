package com.example.fitlog.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity (tableName = "Training_programs")
data class TrainingProgramDB (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "training_program_id")
    var id: Int? = null,
    @ColumnInfo(name = "training_program_name")
    var name: String,
    @ColumnInfo(name = "training_program_desc")
    var desc: String,
    @ColumnInfo(name = "is_current")
    var current: Boolean = true
)