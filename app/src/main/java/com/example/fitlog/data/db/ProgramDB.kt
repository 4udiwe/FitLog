package com.example.fitlog.data.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.data.models.TrainingProgramDB

data class ProgramDB(
    @Embedded
    val program: TrainingProgramDB,
    @Relation(
        entity = TrainingDayDB::class,
        parentColumn = "training_program_id",
        entityColumn = "training_day_id",
        associateBy = Junction(TrainingDayDB::class)
    )
    val trainingDayDBS: List<TrainingDayDB>

)