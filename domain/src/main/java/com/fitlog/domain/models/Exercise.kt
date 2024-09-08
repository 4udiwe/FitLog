package com.fitlog.domain.models

data class Exercise(
    var id: Int? = null,
    var name: String,
    var sets: Int = 4,
    var reps: Int = 12,
    var restTime: Int = 300,
    var dayId: Int? = null
)