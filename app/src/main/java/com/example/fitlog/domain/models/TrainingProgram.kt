package com.example.fitlog.domain.models


data class TrainingProgram (
    var id: Int? = null,
    var name: String,
    var desc: String,
    var current: Boolean = true
)