package com.fitlog.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fitlog.domain.models.TrainingProgram

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
    var current: Boolean = false
){
    fun toDomainTrainingProgramModel(): TrainingProgram{
        return TrainingProgram(
            id = id,
            name = name,
            desc = desc,
            current = current
        )
    }
}