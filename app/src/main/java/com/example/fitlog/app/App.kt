package com.example.fitlog.app

import android.app.Application
import com.example.fitlog.data.db.TrainingProgramDataBase

class App : Application() {
    val db by lazy { TrainingProgramDataBase.createDB(this) }
}